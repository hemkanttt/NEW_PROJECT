package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.MediaType.*;
import com.app.dto.BookDto;
import com.app.service.BookService;
import com.app.service.CategoryService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService catService;

	@PostMapping(value = "/add" , consumes = "multipart/form-data")
	public ResponseEntity<?> addBook(@RequestBody BookDto bDto,	@RequestParam MultipartFile file){
		System.out.println("so");
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addbook(bDto , file)) ;
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateBook(@RequestBody BookDto bDto, MultipartFile file){
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(bDto, file)) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Integer id) {
System.out.println("dsfdsf");
BookDto dto = bookService.getBookById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
		return new ResponseEntity<>("StatusUpdated Sucessfully", HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam String ch) {
	
		return new ResponseEntity<>(bookService.searchBook(ch), HttpStatus.OK);
	}
	
	@GetMapping("/CategorySearch/{catId}")
	public ResponseEntity<?> searchByCategory(@RequestParam Integer catId) {
		
		return new ResponseEntity<>(catService.getBookByCategory(catId), HttpStatus.OK);
	}
	
	
	
	
	

}
