package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PaymentDto;
import com.app.entities.Payment;
import com.app.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<?> getPaymentDetail(@PathVariable Integer orderId){
		return ResponseEntity.status(HttpStatus.OK).body(paymentService.getPaymentByOrderId(orderId));
	}
	
	@PostMapping
	public ResponseEntity<?> addPaymentDetail(@RequestBody PaymentDto payment){
		return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.addPaymentDetail(payment) );
	}
	

}
