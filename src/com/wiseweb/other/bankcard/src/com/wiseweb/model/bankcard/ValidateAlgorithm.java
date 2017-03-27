/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.bankcard.src.com.wiseweb.model.bankcard;

/**
 * @author TearyWang on 2017/1/15.
 */
public interface ValidateAlgorithm {

	/** Gets the algorithm name.
	 *  @return Returns the algorithm name. */
	String getAlgorithmName();

	/** Gets the algorithm validation code length.
	 *  @return Returns the digit length of current algorithm. */
	int validationCodeLength();

	/** Gets the check number string.
	 *  @param cardNumber The bank card number sequence.
	 *  @param hasCheckSeq Indicate the cardNumber contains
	 *                     the check sequence or not.
	 *  @return Returns null if the card number is
	 *          invalid or the check sequence. */
	String getCheckNumber(String cardNumber, boolean hasCheckSeq);

	/** Fill the validation code to target number buffer.
	 *  @param cardNumber The card number buffer. This must have 2 chars.
	 *  @param validateFrom Indicate the validation check start from index.
	 *  @param fillOffset The validation code fill offset,
	 *                    that means the validation calculate
	 *                    form the offset - 1.
	 *  @return Returns true if fill successful or false otherwise.
	 */
	boolean fillCheckNumber(char[] cardNumber, int validateFrom, int fillOffset);
}

class LuhnAlgorithm implements ValidateAlgorithm {

	@Override
	public String getAlgorithmName() { return "Luhn"; }

	@Override
	public int validationCodeLength() { return 1; }

	@Override
	public String getCheckNumber(String cardNumber, boolean hasCheckSeq) {

		if (cardNumber == null || cardNumber.length() == 0)
			return null;

		char[] digs = cardNumber.toCharArray();

		int d02 = hasCheckSeq ? digs.length - 2 : digs.length - 1;
		boolean isD2 = true;
		int checksum = 0, tmpBit = 0;
		for (int i = d02; i >= 0; i--, isD2 = !isD2) {

			if ("- \u3000\f\t\r\n".indexOf(digs[d02]) > -1)
				continue;

			if (digs[d02] < '0' || digs[d02] > '9')
				return null;

			if (isD2) {
				tmpBit = (digs[d02] - '0') << 1;
				if (tmpBit > 9)
					tmpBit = tmpBit - 9;
			}
			else {
				tmpBit = digs[d02] - '0';
			}

			checksum += tmpBit;
		}

		return ((checksum * 9) % 10) + "";
	}

	@Override
	public boolean fillCheckNumber(char[] cardNumber,
		int validateFrom, int fillOffset) {

		if (cardNumber == null || validateFrom < 0 ||
			fillOffset - validateFrom < 2)
			return false;

		int d02 = fillOffset - 1;
		boolean isD2 = true;
		int checksum = 0, tmpBit = 0;
		for (int i = d02; i >= validateFrom; i--, isD2 = !isD2) {

			if (CardInfo.IgnoredCharsInCardNumber.indexOf(cardNumber[d02]) > -1)
				continue;

			if (cardNumber[d02] < '0' || cardNumber[d02] > '9')
				return false;

			if (isD2) {
				tmpBit = (cardNumber[d02] - '0') << 1;
				if (tmpBit > 9)
					tmpBit = tmpBit - 9;
			}
			else {
				tmpBit = cardNumber[d02] - '0';
			}

			checksum += tmpBit;
		}

		int oldchecksum = checksum;
		cardNumber[fillOffset] = (char)((((checksum << 3) + oldchecksum) % 10) + '0');

		return true;
	}
}