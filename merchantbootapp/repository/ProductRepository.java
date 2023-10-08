package org.jsp.merchantbootapp.repository;

import java.util.List;

import org.jsp.merchantbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository  extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.merchant.id=?1")
	public List<Product> findByMerchantId(int merchant_id);
    @Query("select p from Product p where p.brand=?1")
    public List<Product> findProductByBrand(String brand);
    
    @Query("select p from Product p where p.category=?1")
    public List<Product> findProductByCategory(String category);
}
