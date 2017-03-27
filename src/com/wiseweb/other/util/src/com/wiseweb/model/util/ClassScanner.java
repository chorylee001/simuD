/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.util.src.com.wiseweb.model.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author TearyWang on 2017/1/15.
 */
public class ClassScanner {


	private static final String CLASS_SUFFIX = "class";

	public static <T> T instanceByClassName(String className,
		Class<T> superClass, Class<? extends T> defaultClass) {

		Class<? extends T> clazz = null;
		try {
			if (className != null && className.length() > 0) {
				Class<?> objCls = Class.forName(className);
				int modifiers = objCls.getModifiers();
				if (Modifier.isPublic(modifiers) &&
					!Modifier.isAbstract(modifiers) &&
					(superClass == null || superClass.isAssignableFrom(objCls)))

					clazz = objCls.asSubclass(superClass);
			}
		} catch (ClassNotFoundException e) { }

		T ret = null;

		if (clazz != null) {

			try {
				ret = (T) clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e) { }
		}

		// Verify the result initialized, otherwise try default.
		if (ret == null && defaultClass != null && clazz != defaultClass) {

			clazz = defaultClass;
			try {
				ret = (T)clazz.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				/*Never occurs.*/
			}
		}

		return ret;
	}

	public static List<Class<?>> scanClasses(ClassLoader classLoader,
		String packageName, boolean recursive, ClassFilter classFilter)
		throws IOException {

		List<Class<?>> ret = new ArrayList<>();

		String rootPackageDirName = packageName.replace('.', '/');

		Enumeration<URL> dirs = classLoader.getResources(rootPackageDirName);
		// Do for each.
		while (dirs.hasMoreElements()) {

			URL url = dirs.nextElement();
			// Gets the url protocol, may be "file" or "jar"
			String protocol = url.getProtocol();

			if ("file".equals(protocol)) {

				// Read the classes from file.
				// Gets the physics path, and to scan from the physics
				// path.
				String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				URL resourceRootUrl = classLoader.getResource("");
				if (resourceRootUrl != null) {
					String resourceRoot = URLDecoder.decode(
						resourceRootUrl.getFile(), "UTF-8");

					Collections.addAll(ret, findFileEntryAtPackage(
						classLoader, classFilter, packageName, recursive,
						resourceRoot, filePath));
				}
			}
			else if ("jar".equals(protocol)) {

				Collections.addAll(ret, findJarEntryAtPackage(
					classLoader, classFilter, packageName, recursive, url));
			}
		} // End while

		return ret;
	}

	private static Class<?>[] findFileEntryAtPackage(
		ClassLoader classLoader, ClassFilter classFilter,
		String packageName, final boolean recursive,
		String resourceRoot, String fileDir) {

		Map<Class<?>, Class<?>> entries = new HashMap<>();

		File dir = new File(fileDir);

		if (!dir.exists() || !dir.isDirectory())
			return entries.keySet().toArray(new Class[0]);

		final Queue<File> scaningDir = new ArrayDeque<>();
		scaningDir.add(dir);

		List<File> files = new ArrayList<>();
		while (!scaningDir.isEmpty()) {

			File tempDir = scaningDir.remove();

			// Filter all the directory and the class file.
			Collections.addAll(files, tempDir.listFiles(new FileFilter() {

				// Filter, exclude any file which is not class file.
				public boolean accept(File file) {

					if (file.isDirectory()) {
						scaningDir.add(file);
						return false;
					}

					return file.getName().endsWith(CLASS_SUFFIX);
				}
			}));
		}

		for (File file : files) {

			// Remove the class suffix.
			String className = file.getName().substring(0,
				file.getName().length() - CLASS_SUFFIX.length() - 1);
			String classFullName = "";
			if (!recursive)
				classFullName = packageName + '.' + className;
			else {
				try {
					String fullPath = file.getCanonicalPath();
					fullPath = fullPath.substring(
						new File(resourceRoot).getCanonicalPath().length());

					if (fullPath.startsWith(File.separator))
						fullPath = fullPath.substring(1);

					classFullName = fullPath.substring(0,
						fullPath.length() - CLASS_SUFFIX.length() - 1)
						.replace(File.separator, ".");
				}
				catch (IndexOutOfBoundsException | IOException e) { continue; }
			}

			try {

				// We use class loader instead of Class.forName to load class,
				// so that the static method status will not be changing.
				Class<?> clazz = classLoader.loadClass(classFullName);
				if (classFilter.isFiltered(clazz))
					continue;

				entries.put(clazz, clazz);
			}
			catch (ClassNotFoundException e) {

				// Never throw.
			}
		}

		return entries.keySet().toArray(new Class[0]);
	}
	private static Class<?>[] findJarEntryAtPackage(
		ClassLoader classLoader, ClassFilter classFilter,
		String packageName, final boolean recursive,
		URL packageNameURL) throws IOException {

		Map<Class<?>, Class<?>> entries = new HashMap<>();

		// Gets the JAR instance.
		JarFile jar = ((JarURLConnection)packageNameURL.openConnection())
			.getJarFile();
		String rootPackageDirName = packageName.replace(".", "/");

		// Gets the Enumeration from the JAR.
		Enumeration<JarEntry> jarEntries = jar.entries();

		// Enumerate each JarEntry to find the classes.
		while (jarEntries.hasMoreElements()) {

			// The JAR entry may be the META-INF, Directory,
			// class and other resources.
			JarEntry jarEntry = jarEntries.nextElement();

			// Ignore the directory entry.
			if (jarEntry.isDirectory())
				continue;

			// Ignore the file which is not the class.
			String name = jarEntry.getName();
			// Pre-process.
			if (name.charAt(0) == '/')
				name = name.substring(1);

			// Not at the indicated package or not class file, ignore it.
			if (!name.startsWith(rootPackageDirName) ||
				!name.endsWith(CLASS_SUFFIX))
				continue;

			// The entry name may be the class full name,
			// the package name, other resource name, etc.

			int idx = name.lastIndexOf('/');
			// Not the value entry.
			if (idx == -1)
				continue;

			// Remove ".class" suffix and gets the class full name.
			String className = name.substring(0,
				name.length() - CLASS_SUFFIX.length() - 1).replace("/", ".");

			// Verify recursive needs and the class is at the package.
			String classPackageName =
				className.substring(0, className.lastIndexOf("."));
			if (!recursive && !packageName.equals(classPackageName))
				continue;

			try {

				Class<?> clazz = classLoader.loadClass(className);
				if (classFilter.isFiltered(clazz))
					continue;

				entries.put(clazz, clazz);
			}
			catch (NoClassDefFoundError e) {

				// Never occurs.
			}
			catch (ClassNotFoundException e) {

				// Never occurs.
			}
		}
		return entries.keySet().toArray(new Class[0]);
	}

	private ClassScanner() {}
}
