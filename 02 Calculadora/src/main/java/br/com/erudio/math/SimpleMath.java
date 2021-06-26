package br.com.erudio.math;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.erudio.converters.StringConverter;

@Service
public class SimpleMath {
	
	private final int numberTwo = 2;
			
	public double sum(String numberOne, String numberTwo) throws Exception {		
		
		Double result = StringConverter.ConvertToDouble(numberOne) + StringConverter.ConvertToDouble(numberTwo);
		return result;
	}
		
	public double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {				
		Double result = StringConverter.ConvertToDouble(numberOne) - StringConverter.ConvertToDouble(numberTwo);
		return result;
	}
		
	public double mult(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {		
		Double result = StringConverter.ConvertToDouble(numberOne) * StringConverter.ConvertToDouble(numberTwo);
		return result;
	}
		
	public double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {		
		Double result = StringConverter.ConvertToDouble(numberOne) / StringConverter.ConvertToDouble(numberTwo);
		return result;
	}		
	
	public double avg(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {		
		Double result = (StringConverter.ConvertToDouble(numberOne) + StringConverter.ConvertToDouble(numberTwo)) / this.numberTwo;
		return result;
	}
		
	public double sqrt(@PathVariable("number") String number) throws Exception {		
		Double result = Math.sqrt(StringConverter.ConvertToDouble(number));
		return result;
	}
}
