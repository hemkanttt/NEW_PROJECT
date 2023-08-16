package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;

	private String name;

	private String email;

	private String password;

	private String mobNo;

	private String address;

	private String city;

	private String state;

	private String pincode;
	
	private String role;
	
	@Transient
	private String token;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch =FetchType.LAZY)
	List<Orders> orderList=new ArrayList<Orders>();
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch =FetchType.LAZY)
	List<Cart> cartList=new ArrayList<Cart>();


}
