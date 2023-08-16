package com.app.entities;

import javax.persistence.*;

import lombok.Data;


@Entity
@Data
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private Integer quantity;

	@Transient
	private Double totalPrice;

}
