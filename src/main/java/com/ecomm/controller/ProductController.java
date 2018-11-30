package com.ecomm.controller;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecomm.dao.ProductDAO;
import com.ecomm.model.Category;
import com.ecomm.model.Product;

@Controller
public class ProductController {

	@Autowired
	ProductDAO productDAO;

	@RequestMapping(value = "/product")
	public String showProductpage(Model m) {

		List<Product> listProducts = productDAO.findAllProducts();
		m.addAttribute("productDAO", listProducts);

		Product product = new Product();
		m.addAttribute("Product", product);
		return "Product";

	}

	@RequestMapping(value = "/InsertProduct", method = RequestMethod.POST)
	public String insertProduction(@ModelAttribute("product") Product product, Model m) {

		productDAO.addProduct(product);

		Product product1 = new Product();
		m.addAttribute("product", product1);

		List<Product> listProducts = productDAO.findAllProducts();
		m.addAttribute("productlist", listProducts);

		return "product";

	}
	
	@RequestMapping(value="editproducts/productionID")
	public String editProduction(@PathVariable("productID")int  productID, Model m) {
		
		Product product=productDAO.getProduct(productID);
		m.addAttribute("product", product);
		
		List<Product> listProducts = productDAO.findAllProducts();
		m.addAttribute("productlist", listProducts);

	    return "product";
	}
    
	@RequestMapping(value="deletproducts/productionID")
	public String deletProduction(@PathVariable("productID")int  productID, Model m) {
		
		Product product=productDAO.getProduct(productID);
		productDAO.deletProduct(product);  //delet
		
		Product product1=new Product();
		m.addAttribute("product", product1);
		
		List<Product> listProducts = productDAO.findAllProducts();
		m.addAttribute("productlist", listProducts);

		return "product";
	}
	
	public LinkedHashMap<Integer String> getCategoryList(List<Category> listCategory)
	{
		LinkedHashMap<Integer,String> listCategorioes=new LinkedHashMap<Integer,String>();
		
		for(Category category:listCategory)
		{
			listCategory.put(category.getCategoryId(),category.getCategoryName());
		}
		return listCategorioes;
	}
}
