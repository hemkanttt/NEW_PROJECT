package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dto.ReviewDto;
import com.app.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>{

	public List<Review> findByBookId(Integer bookId);
}
