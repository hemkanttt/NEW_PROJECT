package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CategoryDto;
import com.app.dto.SalesByMonthDTO;
import com.app.service.BookOrderService;
import com.app.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private BookOrderService orderService;
	
	@Autowired
	private CategoryService catService;
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrder());
	}
	
	@PutMapping("/updatestatus/{id}/{status}")
	public ResponseEntity<?> updateOrder(@PathVariable Integer id, @PathVariable String status){
		return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(id, status));
	}
	
	@GetMapping("/month-report/{year}")
	public ResponseEntity<List<SalesByMonthDTO>> getTotalSalesByMonth( @PathVariable Integer year) {
        List<SalesByMonthDTO> dtoList =  orderService.getTotalSalesByMonth(year);
        return ResponseEntity.ok(dtoList);
    }
	

	
	

}
