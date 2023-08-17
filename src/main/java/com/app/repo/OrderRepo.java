package com.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Cart;
import com.app.entities.Orders;
import com.app.entities.User;


public interface OrderRepo extends JpaRepository<Orders, Integer> {

	public List<Orders> findByUser(User u);
}
