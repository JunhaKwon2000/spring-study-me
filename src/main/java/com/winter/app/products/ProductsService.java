package com.winter.app.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

	@Autowired
	private ProductsDAO productsDAO;
	
	public List<ProductsVO> list() throws Exception {
		return productsDAO.list();
	}

	public ProductsVO detail(ProductsVO productsVO) {
		return productsDAO.detail(productsVO);
	}

	public int add(ProductsVO productsVO) {
		return productsDAO.add(productsVO);
	}

	public int delete(ProductsVO productsVO) {
		return productsDAO.delete(productsVO);
	}

	public int update(ProductsVO productsVO) {
		return productsDAO.update(productsVO);
	}
	
}
