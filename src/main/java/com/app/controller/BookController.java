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
import com.app.dto.ReviewDto;
import com.app.service.BookService;
import com.app.service.CategoryService;
import com.app.service.ReviewService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/")
	public ResponseEntity<?> createBook(BookDto book, MultipartFile file) {
					
		return new ResponseEntity<>(bookService.addbook(book, file), HttpStatus.CREATED);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateBook(BookDto bookDto, MultipartFile file) {
		return new ResponseEntity<>(bookService.updateBook(bookDto, file), HttpStatus.OK);
	}

	@PostMapping(value = "/add" , consumes = "multipart/form-data")
	public ResponseEntity<?> addBook(@RequestParam("file") MultipartFile file ){
		System.out.println("so");
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addbook(null, file)) ;
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> getAllBookByPagination(

			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,

			@RequestParam(value = "pageSize", defaultValue = "4", required = false) int pageSize,

			@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,

			@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
		
		System.out.println(pageNo+""+pageSize+""+sortBy+""+sortDir);
		return new ResponseEntity<>(bookService.getAllBooks(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBookById(@PathVariable Integer id) {
		System.out.println("dsfdsf");
		BookDto dto = bookService.getBookById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
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
	
	
	@GetMapping("/categories")
	public ResponseEntity<?> getCategories() {
		return new ResponseEntity<>(catService.getAllCategory(), HttpStatus.OK);
	}
	
	
	@PostMapping("/review")
	public ResponseEntity<?> addReview(@RequestBody ReviewDto reviewDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.addReview(reviewDto)) ;
	}
	
	@GetMapping("/review/{id}")
	public ResponseEntity<?> getReviews(@PathVariable  Integer id) {
		return new ResponseEntity<>(reviewService.getReviewByBook(id), HttpStatus.OK);
	}
	
	
	
	
	
	
	

}
