package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDto;
import com.app.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService catService;
	
	@PostMapping
	public ResponseEntity<?> addCategory(@RequestBody CategoryDto catDto){
	return ResponseEntity.status(HttpStatus.CREATED).body(catService.addCategory(catDto));	
	}
	
	
	

	
	

}
