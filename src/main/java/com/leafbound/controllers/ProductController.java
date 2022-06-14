package com.leafbound.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.leafbound.models.Product;
import com.leafbound.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")  // this might need to be changed
@CrossOrigin(origins = {"http://localhost:5500/", "http://127.0.0.1:5500/", "http://127.0.0.1:5501/", "http://localhost:8080/", "http://127.0.0.1:8080/", "http://localhost:5502/", "http://127.0.0.1:5502/"})
@Api(value = "ProductRestController", tags = {"REST controller related to Product Entities"})
public class ProductController {

	@Autowired
	private ProductService productServ;
	
	
	//get all products
	@GetMapping("/products")
	@ApiOperation(value="Find all products")
	public @ResponseBody List<Product> getAll() {
		
		return productServ.getAllProducts();
	}
	
	//create new product
	@PostMapping("/product")
	@ApiOperation(value="create new product entity")
	public @ResponseBody String createProduct(@RequestBody Product product) {
		return productServ.createProduct(product) ? "CREATION SUCCESSFUL" : "CREATION FAILED";

		
	}
}