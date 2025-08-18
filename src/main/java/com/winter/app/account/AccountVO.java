package com.winter.app.account;

import java.time.LocalDate;
import java.util.List;

import com.winter.app.products.ProductsKindVO;
import com.winter.app.products.ProductsVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountVO {

	private String accountNum;
	private String username;
	private Long productNum;
	private LocalDate accountDate;
	private Long accountBalance;
	
	private ProductsVO productsVO;
	private ProductsKindVO productsKindVO;
	
}
