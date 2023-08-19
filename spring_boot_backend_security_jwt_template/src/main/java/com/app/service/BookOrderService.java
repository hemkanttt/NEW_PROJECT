package com.app.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.app.dto.BookOrderDto;
import com.app.dto.SalesByMonthDTO;

public interface BookOrderService {
	
	public List<com.app.dto.BookOrderDto> createOrder(Integer userId, String ptype);
	
	public List<BookOrderDto> getOrderByUser(Integer userId);

	public List<BookOrderDto> getAllOrder();

	public BookOrderDto updateOrder(Integer id, String st);
	
	public List<SalesByMonthDTO> getTotalSalesByMonth(Integer year);
	
	

}
