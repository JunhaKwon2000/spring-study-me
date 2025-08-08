package com.winter.app.products;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsVO {
	
	private Long productNum;
	private String productName;
	private String productContent;
	private LocalDateTime productDate;
	private Double productRate;
	private Long kindNum;
	
	// 1:1
	// 단방향
	private ProductsKindVO productsKindVO;
	
}
