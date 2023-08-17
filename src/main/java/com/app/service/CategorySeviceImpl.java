package com.app.service;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.BookDto;
import com.app.dto.CategoryDto;
import com.app.entities.Book;
import com.app.entities.Category;
import com.app.repo.categoryRepo;
@Service
@Transactional
public class CategorySeviceImpl implements CategoryService {
	@Autowired
	private categoryRepo catRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto addCategory(CategoryDto catDto) {

		return mapper.map(catRepo.save(mapper.map(catDto, Category.class)), CategoryDto.class);
		
	}
	@Override
	public Category getCategory(String catName) {
		return catRepo.findByCategoryName(catName);
	}
	
	@Override
	public List<BookDto> getBookByCategory(Integer id){
		Category cat=catRepo.findById(id).get();
		List<Book> book = cat.getBook();
		List<BookDto>  bd=new ArrayList<BookDto>();
		for (Book book2 : book) {
			bd.add(mapper.map(book2,BookDto.class));
		}
		return bd;
	}

}
