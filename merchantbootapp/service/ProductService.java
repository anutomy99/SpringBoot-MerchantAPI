package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	@Autowired
	private MerchantDao merchantDao;
	
	public ResponseEntity<ResponseStructure<Product>> addProduct(Product product,int merchant_id){
		ResponseStructure<Product> structure=new ResponseStructure<>();
		Optional<Merchant> recMerchant=merchantDao.findById(merchant_id);
		if(recMerchant.isPresent()) {
			Merchant m=recMerchant.get();
			m.getProduct().add(product);
			product.setMerchant(m);
			merchantDao.updateMerchant(m);
			dao.addProduct(product);
			structure.setData(product);
			structure.setMessage("Product Added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<List<Product>>> fetchProductByUserId(int merchant_id){
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		Optional<Merchant> recMerch=merchantDao.findById(merchant_id);
		if(recMerch.isPresent()) {
			structure.setData(dao.fetchProductByUserId(merchant_id));
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<Product>>> fetchProductByBrand(String brand){
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		List<Product> recProduct=dao.fetchProductByBrand(brand);
		if(recProduct.isEmpty()) {
			structure.setData(dao.fetchProductByBrand(brand));
			structure.setMessage("Invalid brand name");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.NOT_FOUND);
		}
		structure.setData(recProduct);
		structure.setMessage("Product Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
	}
	public ResponseEntity<ResponseStructure<List<Product>>> fetchProductByCategory(String category){
		ResponseStructure<List<Product>> structure=new ResponseStructure<>();
		List<Product> recProduct=dao.fetchProductByCategory(category);
		if(recProduct.isEmpty()) {
			structure.setData(dao.fetchProductByCategory(category));
			structure.setMessage("Invalid category");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.NOT_FOUND);
		}
		structure.setData(recProduct);
		structure.setMessage("Product Found");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure,HttpStatus.OK);
	}
	
    public ResponseEntity<ResponseStructure<String>> deleteProduct(int id){
    	ResponseStructure<String> structure=new ResponseStructure<>();
    	Optional<Product> recProduct=dao.findById(id);
    	if(recProduct.isPresent()) {
    		dao.deleteProduct(id);
    		structure.setData("Product Deleted");
    		structure.setMessage("Product Found");
    		structure.setStatusCode(HttpStatus.OK.value());
    		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
    	}
    	throw new IdNotFoundException();
     }

}
