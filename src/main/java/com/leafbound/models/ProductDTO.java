package com.leafbound.models;

import java.time.LocalDate;
import java.util.UUID;

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

	public UUID getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public String getLanguage() {
		return language;
	}

	public LocalDate getPublished_date() {
		return published_date;
	}

	public String getEdition() {
		return edition;
	}

	public String getDescription() {
		return description;
	}

	public double getList_price() {
		return list_price;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setPublished_date(LocalDate published_date) {
		this.published_date = published_date;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setList_price(double list_price) {
		this.list_price = list_price;
	}

	
	
	
	
	
	
}


