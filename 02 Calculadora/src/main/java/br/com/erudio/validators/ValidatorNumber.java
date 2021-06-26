package br.com.erudio.validators;

public class ValidatorNumber {
	
	public static boolean IsNumeric(String strNumber) {
		if (strNumber == null) return false;
		String number = strNumber.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}	
}
