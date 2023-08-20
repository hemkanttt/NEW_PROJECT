package com.app.entities;

import java.util.UUID;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "payment_details")
public class Payment {
	@Id
	@GeneratedValue
	private UUID id;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private Orders order;
	
	private String paymentType;
	
	private Double amount;
	
	private String paymentStatus;

	@Override
	public String toString() {
		return "Payment [id=" + id + ", user=" + user + ", paymentType=" + paymentType + ", amount=" + amount
				+ ", paymentStatus=" + paymentStatus + "]";
	}
	
	

}
