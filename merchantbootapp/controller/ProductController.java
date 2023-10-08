package org.jsp.merchantbootapp.controller;

import java.util.List;

import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	@PostMapping("/productss/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> addProduct(@RequestBody Product product,@PathVariable int merchant_id){
		return service.addProduct(product, merchant_id);
	}
	
	@GetMapping("/productss/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> fetchProductByUserId(@PathVariable int merchant_id){
		return service.fetchProductByUserId(merchant_id);
	}
	@GetMapping("/productss/fetch-by-brand")
	public ResponseEntity<ResponseStructure<List<Product>>> fetchProductByBrand(@RequestParam String brand){
		return service.fetchProductByBrand(brand);
	}
	
	@GetMapping("/productss/fetch-by-category")
	public ResponseEntity<ResponseStructure<List<Product>>> fetchProductByCategory(@RequestParam String category){
		return service.fetchProductByCategory(category);
	}
	@DeleteMapping("/productss/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id){
		return service.deleteProduct(id);
	}

}
