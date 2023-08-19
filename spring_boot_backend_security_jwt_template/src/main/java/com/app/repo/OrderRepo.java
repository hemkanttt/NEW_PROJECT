package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Cart;
import com.app.entities.Orders;
import com.app.entities.User;


public interface OrderRepo extends JpaRepository<Orders, Integer> {

	public List<Orders> findByUser(User u);
	
	@Query("SELECT MONTH(o.date) AS month, SUM(o.price) AS totalSales " +
	           "FROM Orders o where YEAR(o.date)=?1" +
	           "GROUP BY MONTH(o.date) ")
	 List<Object[]> getTotalSalesByMonth(Integer year); 
	
}
