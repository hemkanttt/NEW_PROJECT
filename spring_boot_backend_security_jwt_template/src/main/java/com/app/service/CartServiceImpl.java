package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.app.dto.CartDto;
import com.app.entities.Book;
import com.app.entities.Cart;
import com.app.entities.User;
import com.app.repo.BookRepo;
import com.app.repo.CartRepo;
import com.app.repo.UserRepo;
@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BookRepo bookRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public CartDto addCart(CartDto cartDto) {
		
	    Book book = bookRepo.findById(cartDto.getBookId()).orElseThrow(()->new RuntimeException());
		User user = userRepo.findById(cartDto.getUserId()).orElseThrow(()->new RuntimeException());
		Cart cart = mapper.map(cartDto, Cart.class);
		cart.setTotalPrice(book.getPrice()*cartDto.getQuantity());
		cart.setUser(user);
		cart.setBook(book);
		boolean add = user.getCartList().add(cart);
		System.out.println(cart);
		     
		return mapper.map(cart, CartDto.class);
	}

	@Override
	public List<CartDto> getCartByUser(Integer id) {
		
		List<Cart> carts = cartRepo.findByUser(userRepo.findById(id).get());
		List<CartDto> cartDto=new ArrayList<CartDto>();
		for (Cart cartDto2 : carts) {
			System.out.println(cartDto2);
			CartDto cartDto3 = mapper.map(cartDto2, CartDto.class);
			cartDto3.setUserId(cartDto2.getUser().getId());
			cartDto3.setBookId(cartDto2.getBook().getId());
			cartDto.add(cartDto3) ;
			
		}
		return cartDto;
	}

	
	//will be used when jwt token implemented
	@Override
	public List<CartDto> getAllCartByUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartDto updateQuantity(Integer id, Integer quantity) {
		Cart cart = cartRepo.findById(id).get();
		cart.setQuantity(quantity);
		Cart updateCart = cartRepo.save(cart);
		return mapper.map(updateCart, CartDto.class);
	}

	@Override
	public void deleteCart(Integer id) {
		Cart cart = cartRepo.findById(id).get();
		cartRepo.delete(cart);

	}

}
