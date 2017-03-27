/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.bankcard.src.com.wiseweb.model.bankcard;

import com.wiseweb.other.character.src.com.wiseweb.model.character.CharLinkedIndex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  @see <a href="http://blog.sina.com.cn/s/blog_540316260100wjb6.html">银行卡号编码规则 </a>
 *  @author TearyWang on 2017/1/15.
 */
public class CardInfo {

	public static final String IgnoredCharsInCardNumber = "- \u3000\f\t\r\n";

	/** Gets the publish organization.
	 *  @return Returns the publisher name. */
	public String getOrganization() { return organization; }

	/** Gets a string indicate the card number prefix of the card identifier.
	 *  @return Returns a string contain the most 6 digits
	 *          of the card number cardNumPrefix. */
	public List<String> getCardNumPrefix() { return cardNumPrefix; }

	/** Gets the lengths of the card.
	 *  @return Returns the lengths of the card number. */
	public List<Integer> getLengths() { return lengths; }

	/** Gets a value indicate the card number has a validation digit or not.
	 *  @return Returns true if there's a validation digit or false otherwise. */
	public boolean isHasValidation() { return hasValidation; }

	/** Gets the validate algorithm object.
	 *  @return Returns null if there is no validation digit or the validation
	 *          algorithm object. */
	public ValidateAlgorithm getAlgorithm() { return algorithm; }

	/** Check if the indicated length is a valid length of the card info.
	 *  @param len Target length to be tests.
	 *  @return Returns true if the indicated length is valid
	 *          or false otherwise. */
	public boolean isNumberLengthValid(int len) {
		for (int i = 0; i < lengths.size(); i++)
			if (lengths.get(i) == len)
				return true;

		return false;
	}

	/** Query the card info by the indicated card number.
	 *  @param cardNumber The card number string.
	 *  @return Returns null if the card info is not found or the
	 *          <code>CardInfo</code> object. */
	public static CardInfo query(String cardNumber) {
		return query(cardNumber.toCharArray());
	}

	/** Query the card info by the indicated card number.
	 *  @param cardNumber The card number char array.
	 *  @return Returns null if the card info is not found or the
	 *          <code>CardInfo</code> object. */
	public static CardInfo query(char[] cardNumber) {
		if (cardNumber == null)
			return null;
		return query(cardNumber, 0, cardNumber.length - 1);
	}
	/** Query the card info by the indicated card number.
	 *  @param cardNumber The card number char array.
	 *  @param matchFrom Indicate the match start index.
	 *  @param matchTo Indicate the match end index.
	 *  @return Returns null if the card info is not found or the
	 *          <code>CardInfo</code> object. */
	public static CardInfo query(char[] cardNumber,
		int matchFrom, int matchTo) {
		if (cardNumber == null || cardNumber.length == 0 ||
			matchTo < matchFrom || matchTo >= cardNumber.length)
			return null;

		CharLinkedIndex<CardInfo> cli = rootIndex;
		CharLinkedIndex<CardInfo> temp = null;
		int matchLenEdge = matchTo - matchFrom + 1;
		for (int i = matchFrom; i < matchLenEdge; i++) {
			char ch = cardNumber[i];
			if (IgnoredCharsInCardNumber.indexOf(ch) > -1)
				continue;

			// Bad character found.
			if (ch < '0' || ch > '9')
				return null;

			if ((temp = cli.searchNext(cardNumber[i])) == null)
				break;

			cli = temp;
		}

		if (cli == rootIndex)
			return null;

		CardInfo[] cis = cli.getData();
		while (cis.length == 0 && cli != rootIndex) {
			cli = cli.getParent();
			cis = cli.getData();
		}

		if (cis.length == 0)
			return null;

		return cis[0];
	}

	private String organization;
	private List<String> cardNumPrefix;
	private List<Integer> lengths;
	private boolean isUsed;
	private boolean hasValidation;
	private ValidateAlgorithm algorithm;

	private CardInfo() {}

	private static CharLinkedIndex<CardInfo> rootIndex =
		new CharLinkedIndex<>((char)0, CardInfo.class);
	private static List<CardInfo> loadInfo() {
		List<CardInfo> infos = new ArrayList<>(Data.length);
		for (int i = 0; i < Data.length; i++) {

			CardInfo ci = new CardInfo();

			ci.organization = Data[i][0];
			String[] prefixes = Data[i][1].split(",");
			ci.cardNumPrefix = new ArrayList<>(prefixes.length);
			for (int j = 0; j < prefixes.length; j++) {

				String[] ft = prefixes[j].trim().split("-");
				if (ft.length != 2) {
					ci.cardNumPrefix.add(ft[0]);
				}
				else {
					int from = Integer.parseInt(ft[0]),
						to = Integer.parseInt(ft[1]);
					for (int k = from; k <= to; k++)
						ci.cardNumPrefix.add(k + "");
				}
			}
			ci.cardNumPrefix = Collections.unmodifiableList(ci.cardNumPrefix);

			ci.isUsed = Boolean.parseBoolean(Data[i][2]);

			String[] lengths = Data[i][3].split(",");
			ci.lengths = new ArrayList<>(lengths.length);
			for (int j = 0; j < lengths.length; j++) {

				String[] ft = lengths[j].trim().split("-");
				if (ft.length != 2) {
					ci.lengths.add(Integer.parseInt(ft[0]));
				}
				else {
					int from = Integer.parseInt(ft[0]),
						to = Integer.parseInt(ft[1]);
					for (int k = from; k <= to; k++)
						ci.lengths.add(k);
				}
			}
			ci.lengths = Collections.unmodifiableList(ci.lengths);

			ci.hasValidation = Data[i][4] != null;
			if (ci.hasValidation) {
				ci.algorithm = ValidateAlgorithmFactory
					.factory().getAlgorithm(Data[i][4]);
			}

			infos.add(ci);
		}
		return infos;
	}
	private static final List<CardInfo> info;

	private static final String[][] Data = {
		{ "American Express", "34, 37", "True", "15", "Luhn" },
		{ "Bankcard", "5610, 560221-560225", "False", "16", "Luhn" },
		{ "China UnionPay", "62", "True", "16-19", null },
		{ "Diners Club Carte Blanche", "300-305", "True", "14", "Luhn" },
		{ "Diners Club enRoute", "2014, 2149", "False", "15", null },
		{ "Diners Club International", "36", "True", "14", "Luhn" },
		{ "Diners Club United States & Canada", "54, 55", "True", "16", "Luhn" },
		{ "Discover Card", "6011, 622126-622925, 644-649, 65", "True", "16", "Luhn" },
		{ "InstaPayment", "637-639", "True", "16", "Luhn" },
		{ "JCB", "3528-3589", "True", "16", "Luhn" },
		{ "Laser", "6304, 6706, 6771, 6709", "True", "16-19", "Luhn" },
		{ "Maestro", "5018, 5020, 5038, 6304, 6759, 6761, 6762, 6763", "True", "12-19", "Luhn" },
		{ "MasterCard", "51-55", "True", "16", "Luhn" },
		{ "Solo", "6334, 6767", "False", "16, 18, 19", "Luhn" },
		{ "Switch", "4903, 4905, 4911, 4936, 564182, 633110, 6333, 6759", "False", "16, 18, 19", "Luhn" },
		{ "Visa", "4", "True", "13, 16", "Luhn" },
		{ "Visa Electron", "4026, 417500, 4508, 4844, 4913, 4917", "True", "16", "Luhn" },
	};

	static {
		info = Collections.unmodifiableList(loadInfo());
		for (int i = 0; i < info.size(); i++) {
			CardInfo ci = info.get(i);

			for (int j = 0; j < ci.cardNumPrefix.size(); j++) {
				CharLinkedIndex<CardInfo> cli = rootIndex;

				String num = ci.cardNumPrefix.get(j);
				for (int k = 0; k < num.length(); k++) {
					cli = cli.addNext(num.charAt(k));
				}

				cli.addData(ci);
			}
		}
	}
}
