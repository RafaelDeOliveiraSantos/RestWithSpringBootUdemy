package br.com.erudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.math.SimpleMath;
import br.com.erudio.validators.ValidatorPath;

@RestController
public class MathController {
			
	@Autowired //faz injeção de dependência, evitando de instanciar a classe SimplMath, para isso, na classe SimpleMath tem a annotation @Service
	private SimpleMath math;
	
	private final ValidatorPath validatorPath;
	
	public MathController () {
		validatorPath = new ValidatorPath();
	}
		
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
			
		validatorPath.ValidatePathVariable(numberOne, numberTwo);
		return math.sum(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		validatorPath.ValidatePathVariable(numberOne, numberTwo);
		return math.sub(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public double mult(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		validatorPath.ValidatePathVariable(numberOne, numberTwo);
		return math.mult(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		validatorPath.ValidatePathVariable(numberOne, numberTwo);
		return math.div(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/avg/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public double avg(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		
		validatorPath.ValidatePathVariable(numberOne, numberTwo);
		return math.avg(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/sqrt/{number}", method = RequestMethod.GET)
	public double sqrt(@PathVariable("number") String number) throws Exception {
		
		validatorPath.ValidatePathVariable(number);
		return math.sqrt(number);
	}		
}
