package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.adapter.DozerAdapter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerAdapter.parseObject(person, Person.class);
		var vo = DozerAdapter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {					
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		var entityUpdate = DozerAdapter.parseObject(person, entity.getClass());
		
		var vo = DozerAdapter.parseObject(repository.save(entityUpdate), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	public PersonVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerAdapter.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {			
		return DozerAdapter.parseListObjects(repository.findAll(), PersonVO.class);
	}
		
	public Page<PersonVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}
	
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		var page = repository.findPersonByName(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}	
	
	private PersonVO convertToPersonVO(Person entity)
	{
		return DozerAdapter.parseObject(entity, PersonVO.class);
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePerson(id);
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerAdapter.parseObject(entity, PersonVO.class);
	}
}
