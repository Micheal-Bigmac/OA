package com.oa.service;

import java.io.Serializable;
import java.util.List;
import com.oa.model.Product;

public interface ProductService {
	
	public Serializable addProduct(Product product);
	
	public void deleteProduct(Product product);
	
	public void updateProduct(Product product);
	public Product getProduct(Serializable id);
	
	public List<Product> getAllProducts(Class clazz,String hql);
	
	public List<Product> getPageProducts(int index, Class clazz,String hql);
	
	public void deleteProducts(String []ids);
	
}
