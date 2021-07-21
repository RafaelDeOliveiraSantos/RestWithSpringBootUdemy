package br.com.erudio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.adapter.DozerAdapter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository repository;
	
	public BookVO create(BookVO book) {
		var entity = DozerAdapter.parseObject(book, Book.class);
		var vo = DozerAdapter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public BookVO update(BookVO book) {					
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
								
		var entityUpdate = DozerAdapter.parseObject(book, entity.getClass());
		
		var vo = DozerAdapter.parseObject(repository.save(entityUpdate), BookVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
	public BookVO findById(Long id) {
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		return DozerAdapter.parseObject(entity, BookVO.class);
	}
	
	public List<BookVO> findAll() {			
		return DozerAdapter.parseListObjects(repository.findAll(), BookVO.class);
	}	

}
