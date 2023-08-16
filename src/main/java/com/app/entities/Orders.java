package com.app.entities;

import java.time.LocalDate;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Integer id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Book book;

	private int quantity;

	private String paymentType;

	private String orderNumber;

	private LocalDate date;

	private String status;

}
