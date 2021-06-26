package br.com.erudio.converters;

import br.com.erudio.validators.ValidatorNumber; 

public class StringConverter {
		
	public static Double ConvertToDouble(String strNumber) {
		if (strNumber == null) return 0D;
		String number = strNumber.replaceAll(",", ".");		
		if (ValidatorNumber.IsNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}
}
