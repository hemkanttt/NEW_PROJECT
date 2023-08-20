package com.app.dto;

import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentDto {
	
	private UUID id;
	

	private Integer userID;
	
	
	private Integer orderID;
	
	private String paymentType;
	
	private Double amount;
	
	private String paymentStatus;

	@Override
	public String toString() {
		return "Payment [id=" + id + ", user=" + userID + ", paymentType=" + paymentType + ", amount=" + amount
				+ ", paymentStatus=" + paymentStatus + "]";
	}
	
	

}
