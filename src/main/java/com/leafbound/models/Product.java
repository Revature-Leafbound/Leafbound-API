package com.leafbound.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name="products")
@Data
@ApiModel(value="prodcuts", description="book_description")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	  @ApiModelProperty(name="id", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
		private int id;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
	private Author author; 
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
	private Publisher publisher;
	
	@Column(name="isbn", nullable=false)
	  @ApiModelProperty(name="isbn", 
	    notes="an Integer value that serves as the unique identier for any comments entity",//notes will change on each column
	    required = true,
	    value = "1")//may need to change value
	private String isbn;
	
	@Column(name="genre", nullable=false)
	  @ApiModelProperty(name="genre", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
	private String genre;
	
	@Column(name="title", nullable=false)
	  @ApiModelProperty(name="title", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
	private String title;
	
	@Column(name="lang", nullable=false)
	  @ApiModelProperty(name="lang", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
	private String language;
	
	@Column(name="published_date", nullable=false)
	  @ApiModelProperty(name="published_date", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
	private LocalDate published_date;
	
	@Column(name="edition", nullable=false)
	  @ApiModelProperty(name="edition", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
	private String edition;
	
	@Column(name="description", nullable=false)
	  @ApiModelProperty(name="description", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
	private String description;
	
	@Column(name="list_price", nullable=false)
	  @ApiModelProperty(name="list_price", 
	    notes="an Integer value that serves as the unique identier for any comments entity",
	    required = true,
	    value = "1")
	private double list_price;

	public Product() {
		super();
	}

	public Product(Author author, Publisher publisher, String isbn, String genre, String title, String language,
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

	public Product(int id, Author author, Publisher publisher, String isbn, String genre, String title, String language,
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
