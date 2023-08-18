package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CartDto;
import com.app.dto.UserDto;
import com.app.entities.User;
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
	
//	@GetMapping("/signin")
//	public ResponseEntity<?> getUserByEmailandPassword(@RequestBody UserDto userDto) {
//		return ResponseEntity.status(HttpStatus.FOUND).body(userService.getUserByEmailAndPassword(userDto));
//	}
//
//	@PostMapping("/signup")
//	public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
//	
//	}
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.updateUser(user));
	}

	@PostMapping("/cart")
	public ResponseEntity<?> addToCart(@RequestBody CartDto cartDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addCart(cartDto));
	}

	@GetMapping("/order/{ptype}/{id}")
	public ResponseEntity<?> order(@PathVariable Integer userId, @PathVariable String ptype) {
		System.out.println(userId);
		System.out.println(ptype);
		orderService.createOrder(userId, ptype);
		return ResponseEntity.status(HttpStatus.OK).body("Order placed");
	}

	@GetMapping("/order/{id}")
	
	public ResponseEntity<?> getOrderByUser(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByUser(id));
	}

	@GetMapping("/cartQuantUpdate/{id}/{quant}")
	public ResponseEntity<?> updateQuantity(@PathVariable Integer id, Integer quant) {
		return ResponseEntity.status(HttpStatus.OK).body(cartService.updateQuantity(id, quant));
	}
	
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable Integer id) {
		cartService.deleteCart(id);
		return new ResponseEntity<>("Cart Deleted Sucessfully", HttpStatus.OK);
	}
	
	

}
