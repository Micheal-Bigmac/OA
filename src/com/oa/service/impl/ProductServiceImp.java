package com.oa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.oa.dao.impl.SuperDao;
import com.oa.model.Product;
import com.oa.service.ProductService;
@Component("productService")
public class ProductServiceImp implements ProductService {

	private SuperDao superDao;
	public Serializable addProduct(Product product) {
		return superDao.add(product);
	}

	public void deleteProduct(Product product) {
		superDao.delete(product);
	}

	public Product getProduct(Serializable id) {
		return (Product) superDao.select(Product.class, id);
	}

	public List<Product> getAllProducts(Class clazz, String hql) {
		return ObjectToProduct(superDao.getAllObjects(clazz, hql));
	}
	private List<Product> ObjectToProduct(List<Object> list){
		List<Product> products=new ArrayList<Product>();
		for(Object o: list){
			products.add((Product)o);
		}
		return products;
	}

	public List<Product> getPageProducts(int index, Class clazz, String hql) {
		return ObjectToProduct(superDao.getPage(index, clazz, hql));
	}

	public void deleteProducts(String[] ids) {
		superDao.deleteList(Product.class, ids, "delete from Product p where p.id ");
	}

	@Resource
	public void setSuperDao(SuperDao superDao) {
		this.superDao = superDao;
	}

	public void updateProduct(Product product) {
		superDao.update(product);
	}

}
