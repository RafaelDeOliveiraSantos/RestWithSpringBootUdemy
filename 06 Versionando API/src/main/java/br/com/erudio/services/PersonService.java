package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.adapter.DozerAdapter;
import br.com.erudio.adapter.Custom.PersonAdapter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonAdapter personAdapter;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerAdapter.parseObject(person, Person.class);
		var vo = DozerAdapter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		//Foi criado um adapter específico devido a nova propriedade birthday do VOV2 que não existe na entidade Person, 
		//portanto, o dozer não conseguiria converter.
		var entity = personAdapter.convertVOToEntity(person);
		var vo = personAdapter.convertEntityToVO(repository.save(entity));
		return vo;
	}	
	
	public PersonVO update(PersonVO person) {					
		var entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
				
		/*
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		*/
		
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
}
