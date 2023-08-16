package com.app.entities;

import javax.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "books")
public class Book{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private Integer id;

	private String bookName;

	private String description;

	private String author;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	private String isbnNo;

	private String language;

	private Double price;

	private String img;

	
}
