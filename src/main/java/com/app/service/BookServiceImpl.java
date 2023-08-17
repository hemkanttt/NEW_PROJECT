package com.app.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.BookDto;
import com.app.dto.CategoryDto;
import com.app.dto.ReviewDto;
import com.app.entities.Book;
import com.app.entities.Cart;
import com.app.entities.Category;
import com.app.entities.Review;
import com.app.exceptions.ResourceNotFoundException;
import com.app.payloads.BookResponse;
import com.app.repo.BookRepo;
import com.app.repo.CartRepo;
import com.app.repo.categoryRepo;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private categoryRepo cateRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryService catService;

	@Autowired
	private FileService fileService;

	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ReviewService reviewService;

	@Value("${project.image}")
	private String path;

	@Override
	public BookDto addbook(BookDto bdto, MultipartFile file) {
		System.out.println("servide before");
		try {
			fileService.uploadImage(path, file);
		} catch (IOException e) {

			e.printStackTrace();
		}
		Category cat = catService.getCategory(bdto.getCategory());
		Book book = mapper.map(bdto, Book.class);
		book.setCategory(cat);

		if (!file.isEmpty()) {
			book.setImg(file.getOriginalFilename());
		} else {
			book.setImg("default.jpg");
		}

		Book newBook = bookRepo.save(book);
		System.out.println("servide savebook");

		if (newBook != null) {
			try {
				fileService.uploadImage(path, file);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		System.out.println("servide after updload");

		return mapper.map(bookRepo.save(book), BookDto.class);
	}

	@Override
	public BookDto getBookById(Integer id) {
		Book b = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("BookNotFound"));
		List<ReviewDto> list= reviewService.getReviewByBook(id);
	
	Double sum=0.0;
	  for (ReviewDto reviewDto : list) {
		sum=sum+reviewDto.getRating();
	}
		BookDto bookDto = mapper.map(b, BookDto.class);
		bookDto.setAvgRating(sum/list.size());
		return bookDto;
	}

	@Override
	public BookDto updateBook(BookDto bookDto, MultipartFile file) {
		Book b = bookRepo.findById(bookDto.getId()).orElseThrow(() -> new ResourceNotFoundException("BookNotFound"));

		Category cat = catService.getCategory(bookDto.getCategory());

		if (file != null) {
			b.setImg(file.getOriginalFilename());
			try {
				fileService.uploadImage(path, file);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		b.setBookName(bookDto.getBookName());
		b.setAuthor(bookDto.getAuthor());
		b.setCategory(cat);
		b.setIsbnNo(bookDto.getIsbnNo());
		b.setPrice(bookDto.getPrice());
		b.setLanguage(bookDto.getLanguage());
		b.setDescription(bookDto.getDescription());

		return mapper.map(bookRepo.save(b), BookDto.class);
	}

	@Override
	public void deleteBook(Integer id) {

		Book b = bookRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("BookNotFound"));
		cartRepo.findByBook(b).forEach(cart -> cartRepo.delete(cart));
		b.setStatus(0);
		bookRepo.save(b);
	}

	@Override
	public List<BookDto> searchBook(String ch) {
		return bookRepo.search(ch).stream().map((e) -> mapper.map(e, BookDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public BookResponse getAllBooks(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Book> pageList = bookRepo.findAll(pageable);

		List<Book> list = pageList.getContent();

		List<BookDto> bookDtoList = list.stream().map((book) -> mapper.map(book, BookDto.class))
				.collect(Collectors.toList());

		BookResponse bookResponse = new BookResponse();
		bookResponse.setBook(bookDtoList);
		bookResponse.setPageNumber(pageList.getNumber());
		bookResponse.setPageSize(pageList.getSize());
		bookResponse.setTotalElements(pageList.getTotalElements());
		bookResponse.setTotalPages(pageList.getTotalPages());
		bookResponse.setLastPage(pageList.isLast());

		return bookResponse;
	}

}
