package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.dto.WishlistDto;
import com.app.entities.Book;
import com.app.entities.User;
import com.app.entities.Wishlist;
import com.app.repo.BookRepo;
import com.app.repo.UserRepo;
import com.app.repo.WishListRepo;

public class WishlistServiceImpl implements WishlistService {
	
	@Autowired
	private WishListRepo wishlistRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	

	@Override
	public WishlistDto addToWishList(WishlistDto wishlistDto) {
		Book book=bookRepo.findById(wishlistDto.getBookId()).get();
		User user=userRepo.findById(wishlistDto.getUserID()).get();
		Wishlist wishList=mapper.map(wishlistDto,Wishlist.class);
		wishList.setUser(user);
		wishList.setBook(book);
		return mapper.map(wishlistRepo.save(wishList), WishlistDto.class);
	}

}
