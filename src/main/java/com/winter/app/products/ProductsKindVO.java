package com.winter.app.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductsKindVO {
	
	private Long kindNum;
	private String kindName;
	
	// 1:N
	// private List<ProductsVO> list;
	
}
