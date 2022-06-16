package com.leafbound.models;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;

@Data
public class ProductDTO {
	private UUID id;
	private String author;
	private String publisher;
	private String isbn;
	private String genre;
	private String title;
	private String language;
	private LocalDate published_date;
	private String edition;
	private String description;
	private double list_price;
	
	public ProductDTO() {
		super();
	}

	public ProductDTO(String author, String publisher, String isbn, String genre, String title, String language,
			LocalDate published_date, String edition, String description, double list_price) {
		super();
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.genre = genre;
		this.title = title;
		this.language = language;
		this.published_date = published_date;
		this.edition = edition;
		this.description = description;
		this.list_price = list_price;
	}

	public ProductDTO(UUID id, String author, String publisher, String isbn, String genre, String title,
			String language, LocalDate published_date, String edition, String description, double list_price) {
		super();
		this.id = id;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
		this.genre = genre;
		this.title = title;
		this.language = language;
		this.published_date = published_date;
		this.edition = edition;
		this.description = description;
		this.list_price = list_price;
	}
	
	
	
	
	
}


