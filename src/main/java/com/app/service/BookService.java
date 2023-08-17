package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.BookDto;

public interface BookService {
	
	public BookDto addbook(BookDto bdto , MultipartFile file) ;

	BookDto getBookById(Integer id);
	
	public BookDto updateBook(BookDto bookDto, MultipartFile file);
	
	public void deleteBook(Integer id) ;
	
	public List<BookDto> searchBook(String ch);
	
}
