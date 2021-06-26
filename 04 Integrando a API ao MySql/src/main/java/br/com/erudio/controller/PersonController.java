package br.com.erudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService services;
				
	@GetMapping
	public List<Person> findAll() {
		return services.findAll();		
	}
	
	@GetMapping("/{id}")
	public Person findById(@PathVariable("id") Long id) {
		return services.findById(id);		
	}
	
	@PostMapping
	public Person create(@RequestBody Person person) {
		return services.create(person);		
	}	
	
	@PutMapping
	public Person update(@RequestBody Person person) {
		return services.update(person);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}
	
	/*
	 * Na primeira implementação, os métodos tinham a anotação abaixo, porém, existem métodos próprios para simplificar a implementação, 
	 * e também o producers e consumes são json por padrão, portanto, podemos deixar o código mais enxuto.
	 * @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE))
	 */	
	
}
