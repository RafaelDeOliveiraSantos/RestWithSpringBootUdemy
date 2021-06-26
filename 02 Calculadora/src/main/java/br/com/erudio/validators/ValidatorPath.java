package br.com.erudio.validators;

import br.com.erudio.exception.UnsuportedMathOperationException;

public class ValidatorPath {
	
	private final String UnsuportedMathException = "Please set a numeric value!";		
		
	public void ValidatePathVariable(String strNumberOne, String strNumberTwo) {
		if (!ValidatorNumber.IsNumeric(strNumberOne) || !ValidatorNumber.IsNumeric(strNumberTwo)) {
			throw new UnsuportedMathOperationException(UnsuportedMathException);
		}
	}	
	public void ValidatePathVariable(String strNumberOne) {
		if (!ValidatorNumber.IsNumeric(strNumberOne)) {
			throw new UnsuportedMathOperationException(UnsuportedMathException);
		}
	}	
}
