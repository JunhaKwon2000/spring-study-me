package com.winter.app.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/products/*")
public class ProductsController {
	
	@Autowired
	private ProductsService productsService;

	@GetMapping("list")
	public void list(Model model) throws Exception {
		List<ProductsVO> result = productsService.list();
		model.addAttribute("list", result);
	}
	
	@GetMapping("detail")
	public void detail(ProductsVO productsVO, Model model) throws Exception {
		ProductsVO result = productsService.detail(productsVO);
		model.addAttribute("detail", result);
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "products/form";
	}
	
	@PostMapping("add")
	public String add(ProductsVO productsVO, Model model) throws Exception {
		int result = productsService.add(productsVO);
		String msg = "Action Failed";
		String url = "./list";
		if (result > 0) {
			msg = "Product Added";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@GetMapping("update")
	public String updateView(ProductsVO productsVO, Model model) throws Exception {
		ProductsVO result = productsService.detail(productsVO);
		model.addAttribute("detail", result);
		return "products/form";
	}
	
	@PostMapping("update")
	public String update(ProductsVO productsVO, Model model) throws Exception {
		int result = productsService.update(productsVO);
		String msg = "Action Failed";
		String url = "./detail?productNum="+productsVO.getProductNum();
		if (result > 0) {
			msg = "Product Updated";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@PostMapping("delete")
	public String delete(ProductsVO productsVO, Model model) throws Exception {
		int result = productsService.delete(productsVO);
		String msg = "Action Failed";
		String url = "./list";
		if (result > 0) {
			msg = "Product Deleted";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
}
