package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    @Autowired
	private ProductRepository repository;
    
    public Product addProduct(Product product) {
    	return repository.save(product);
    	
    }
    public Optional<Product> findById(int id){
    	return repository.findById(id);
    }
    public List<Product> fetchProductByUserId(int merchant_id){
    	return repository.findByMerchantId(merchant_id);
    }
    
    public List<Product> fetchProductByBrand(String brand){
    	return repository.findProductByBrand(brand);
    }
    
    public List<Product> fetchProductByCategory(String category){
    	return repository.findProductByCategory(category);
    }
    
    public boolean deleteProduct(int id) {
    	Optional<Product> recProduct=findById(id);
    	if(recProduct.isPresent()) {
    		repository.delete(recProduct.get());
    		return true;
    	}
    	return false;
    }
    
 
}
