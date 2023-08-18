package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.ReviewDto;



public interface ReviewService {
	
	public ReviewDto addReview(ReviewDto rv);
	
	public List<ReviewDto> getReviewByBook(Integer bookId);
	
	

}
