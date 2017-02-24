package com.wiseweb.util;

import com.wiseweb.entity.HbasePageBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;

/**
 * Created by Chory on 2017/2/24 0024.
 */
public class HbaseBase {

    private static Configuration config = null;
    private static HTablePool tp = null;
    static {
        // 加载集群配置
        config = HBaseConfiguration.create();
        config.set("hbase.master", "10.1.8.186:60010");
        config.set("hbase.zookeeper.quorum", "10.1.8.186");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        // 创建表池
        tp = new HTablePool(config, 10000);
    }

    /**
     * 获取hbase表
     * 已弃用，0.98版本以后大胆的从Table中获取数据就可以了，
     * 所有的资源和管理都有Connectionl类在内部进行完成
     * @param tableName 表名
     * @return
     */
    @Deprecated
    public static HTableInterface getTable(String tableName) {

        if (StringUtils.isEmpty(tableName))
            return null;

        return tp.getTable(getBytes(tableName));
    }

    /**
     * 删除表
     * @param tableName 表名
     */
    public static void dropTable(String tableName){

        try {
            HBaseAdmin hBaseAdmin = new HBaseAdmin(config);
            hBaseAdmin.disableTable(tableName);
            hBaseAdmin.deleteTable(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除表数据
     * @param tableName 表名
     * @param rowKey
     */
    public static void deleteRowByRowkey(String tableName,String rowKey){

        try {
            HTable hTable = new HTable(config,tableName);
            Delete delete = new Delete(rowKey.getBytes());
            List list = new ArrayList();
            list.add(delete);
            hTable.delete(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询表所有数据
     * @param tableName 表名称
     */
    public static List<Cell>  queryAll(String tableName) throws IOException{

        Connection connection = ConnectionFactory.createConnection(config);
        Table table = connection.getTable(TableName.valueOf(tableName));
        ResultScanner rs = table.getScanner(new Scan());
        List<Cell> tableData = new ArrayList<>();
        for(Result result : rs){
            for(Cell cell: result.rawCells()){
                tableData.add(cell);
            }
        }
        return tableData;
    }
    /* 转换byte数组 */
    public static byte[] getBytes(String str) {
        if (str == null)
            str = "";

        return Bytes.toBytes(str);
    }

    // 获取扫描器对象
    private static Scan getScan(String startRow, String stopRow) {
        Scan scan = new Scan();
        scan.setStartRow(getBytes(startRow));
        scan.setStopRow(getBytes(stopRow));
        return scan;
    }

    /**
     * 获取数据
     * @param tableName
     * @param startRow
     * @param stopRow
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static HbasePageBean getDataMap(String tableName, String startRow,
                                           String stopRow, Integer currentPage, Integer pageSize){
        List<Map<String, String>> mapList = null;
        mapList = new LinkedList<Map<String, String>>();
        ResultScanner scanner = null;
        // 为分页创建的封装类对象，下面有给出具体属性
        HbasePageBean pageBean = null;
        try {
            // 获取最大返回结果数量
            if (pageSize == null || pageSize == 0L)
                pageSize = 100;

            if (currentPage == null || currentPage == 0)
                currentPage = 1;

            // 计算起始页和结束页
            Integer firstPage = (currentPage - 1) * pageSize;

            Integer endPage = firstPage + pageSize;

            // 从表池中取出HBASE表对象
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(TableName.valueOf(tableName));
            // 获取筛选对象
            Scan scan = getScan(startRow, stopRow);
            // 给筛选对象放入过滤器(true标识分页,具体方法在下面)
            scan.setFilter(packageFilters(true));
            // 缓存1000条数据
            scan.setCaching(1000);
            scan.setCacheBlocks(false);
            scanner = table.getScanner(scan);
            int i = 0;
            List<byte[]> rowList = new LinkedList<byte[]>();
            // 遍历扫描器对象， 并将需要查询出来的数据row key取出
            for (Result result : scanner) {
                String row = toStr(result.getRow());
                if (i >= firstPage && i < endPage) {
                    rowList.add(getBytes(row));
                }
                i++;
            }

            // 获取取出的row key的GET对象
            List<Get> getList = getList(rowList);
            Result[] results = table.get(getList);
            // 遍历结果
            for (Result result : results) {
                Map<byte[], byte[]> fmap = packFamilyMap(result);
                Map<String, String> rmap = packRowMap(fmap);
                mapList.add(rmap);
            }

            // 封装分页对象
            pageBean = new HbasePageBean();
            pageBean.setCurrentPage(currentPage);
            pageBean.setPageSize(pageSize);
            pageBean.setTotalCount(i);
            pageBean.setTotalPage(getTotalPage(pageSize, i));
            pageBean.setResultList(mapList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeScanner(scanner);
        }

        return pageBean;
    }

    /**
     * 封装每行数据
     */
    private static Map<String, String> packRowMap(Map<byte[], byte[]> dataMap) {

        Map<String, String> map = new LinkedHashMap<String, String>();
        for (byte[] key : dataMap.keySet()) {
            byte[] value = dataMap.get(key);
            map.put(toStr(key), toStr(value));

        }
        return map;
    }
    /**
     * 封装配置的所有字段列族
     */
    private static Map<byte[], byte[]> packFamilyMap(Result result) {

        Map<byte[], byte[]> dataMap = null;
        dataMap = new LinkedHashMap<byte[], byte[]>();
        dataMap.putAll(result.getFamilyMap(getBytes("family1")));
        dataMap.putAll(result.getFamilyMap(getBytes("family2")));
        return dataMap;
    }

    private static int getTotalPage(int pageSize, int totalCount) {
        int n = totalCount / pageSize;
        if (totalCount % pageSize == 0) {
            return n;
        } else {
            return ((int) n) + 1;
        }
    }

    /* 根据ROW KEY集合获取GET对象集合 */
    private static List<Get> getList(List<byte[]> rowList) {
        List<Get> list = new LinkedList<Get>();
        for (byte[] row : rowList) {
            Get get = new Get(row);

            get.addColumn(getBytes("family1"), getBytes("column1"));
            get.addColumn(getBytes("family1"), getBytes("column2"));
            get.addColumn(getBytes("family2"), getBytes("column1"));
            list.add(get);
        }
        return list;
    }
    /**
     * 封装查询条件
     */
    private static FilterList packageFilters(boolean isPage) {
        FilterList filterList = null;
        // MUST_PASS_ALL(条件 AND) MUST_PASS_ONE（条件OR）
        filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        Filter filter1 = null;
        Filter filter2 = null;
        filter1 = newFilter(getBytes("family1"), getBytes("column1"),
                CompareOp.EQUAL, getBytes("condition1"));
        filter2 = newFilter(getBytes("family2"), getBytes("column1"),
                CompareOp.LESS, getBytes("condition2"));
        filterList.addFilter(filter1);
        filterList.addFilter(filter2);
        if (isPage) {
            filterList.addFilter(new FirstKeyOnlyFilter());
        }
        return filterList;
    }

    /**
     * 将byte数组转换为字符串
     * @param bt
     * @return
     */
    private static String toStr(byte[] bt) {
        return Bytes.toString(bt);
    }
    private static Filter newFilter(byte[] f, byte[] c, CompareFilter.CompareOp op, byte[] v) {
        return new SingleColumnValueFilter(f, c, op, v);
    }

    /**
     * 关闭扫描器
     * @param scanner
     */
    private static void closeScanner(ResultScanner scanner) {
        if (scanner != null)
            scanner.close();
    }
}
