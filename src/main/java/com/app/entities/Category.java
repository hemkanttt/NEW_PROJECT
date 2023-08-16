package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer id;

	private String categoryName;

	private String description;

	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Book> book = new ArrayList<Book>();

}
