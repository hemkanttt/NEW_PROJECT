package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CartDto;
import com.app.dto.UserDto;
import com.app.service.BookOrderService;
import com.app.service.CartService;
import com.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private BookOrderService orderService;
	
	@GetMapping
	public ResponseEntity<?> getUserByEmailandPassword(@RequestBody UserDto userDto){
	return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserByEmailAndPassword(userDto));
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
	}
	
	@PostMapping("/cart")
	public ResponseEntity<?> addToCart(@RequestBody CartDto cartDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCart(cartDto));
	}
	
	@GetMapping("/order/{ptype}/{id}")
	public ResponseEntity<?> order(@PathVariable Integer id,@PathVariable String ptype){
		System.out.println(id);
		System.out.println(ptype);
		orderService.createOrder(id, ptype);
		return ResponseEntity.status(HttpStatus.OK).body("Order placed");
	}
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<?> getOrderByUser(@PathVariable Integer id){
	return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByUser(id));
	}
	
	
	
	
	
	
}
