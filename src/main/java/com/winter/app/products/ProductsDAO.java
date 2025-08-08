package com.winter.app.products;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductsDAO {
	
	List<ProductsVO> list() throws Exception;

	ProductsVO detail(ProductsVO productsVO);

	int add(ProductsVO productsVO);
	
	int delete(ProductsVO productsVO);

	int update(ProductsVO productsVO);
	
}
