package com.leafbound.models;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
@ApiModel(value = "products", description = "book_description")
public class Product {

	@Id
	@GeneratedValue(generator = "uuid4")
	@Column(name = "id")
	@ApiModelProperty(name = "id", notes = "an Integer value that serves as the unique identier for any product entity", required = true, value = "1")
	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID id;

	@Column(name = "author", nullable = false)
	@ApiModelProperty(name = "author", notes = "an String value that serves as the author name of the product", required = true, value = "1") // may
																																				// need
																																				// to
																																				// change
																																				// value
	private String author;

	@Column(name = "publisher", nullable = false)
	@ApiModelProperty(name = "publisher", notes = "an String value that serves as the publisher name of the product", required = true, value = "1") // may
																																					// need
																																					// to
																																					// change
																																					// value
	private String publisher;

	@Column(name = "isbn", nullable = false)
	@ApiModelProperty(name = "isbn", notes = "a String that serves as the ISBN for the product", required = true, value = "1") // may
																																// need
																																// to
																																// change
																																// value
	private String isbn;

	@Column(name = "genre", nullable = false)
	@ApiModelProperty(name = "genre", notes = "a String that serves as the genre tag for the product", required = true, value = "1")
	private String genre;

	@Column(name = "title", nullable = false)
	@ApiModelProperty(name = "title", notes = "a String that serves as the title of the product", required = true, value = "1")
	private String title;

	@Column(name = "lang", nullable = false)
	@ApiModelProperty(name = "lang", notes = "a String that serves as the language tag for the product", required = true, value = "1")
	private String language;

	@Column(name = "published_date", nullable = false)
	@ApiModelProperty(name = "published_date", notes = "a LocalDate that serves as the published date of the product", required = true, value = "1")
	private LocalDate published_date;

	@Column(name = "edition", nullable = false)
	@ApiModelProperty(name = "edition", notes = "a String that serves as the edition version of the product", required = true, value = "1")
	private String edition;

	@Column(name = "description", nullable = false)
	@ApiModelProperty(name = "description", notes = "a String that serves as the product description", required = true, value = "1")
	private String description;

	@Column(name = "list_price", nullable = false)
	@ApiModelProperty(name = "list_price", notes = "a double precision floating point that serves as the list price of the product", required = true, value = "1")
	private double list_price;

	public Product() {
		super();
	}

	public Product(String author, String publisher, String isbn, String genre, String title, String language,
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

	public Product(UUID id, String author, String publisher, String isbn, String genre, String title, String language,
			LocalDate published_date, String edition, String description, double list_price) {
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
