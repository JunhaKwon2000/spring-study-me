package com.winter.app.products;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsKindVO {
	
	private Long kindNum;
	private String kindName;
	
	// 1:N
	// private List<ProductsVO> list;
	
}
