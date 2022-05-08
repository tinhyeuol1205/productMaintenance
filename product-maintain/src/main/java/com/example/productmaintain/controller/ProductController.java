package com.example.productmaintain.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.productmaintain.business.Product;
import com.example.productmaintain.data.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("productMaint1")
public class ProductController {
    private final ProductRepository productRepo;
    @Autowired
    public ProductController(ProductRepository productRepo){
        this.productRepo = productRepo;
    }
    @GetMapping("")
    public String showHomePage(){
        return "index";
    }
    @GetMapping("/displayProducts")
    public String showProducts(Model model){
        List<Product> products = new ArrayList<>();
        productRepo.getProducts().forEach(products::add);
        model.addAttribute("products",products);
        return "displayProducts";
    }

    @GetMapping("/addProduct")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String showAddForm(@ModelAttribute Product product) {
        System.out.println("Product saved: "+product.getCode());
        productRepo.save(product);
        return "redirect:/productMaint1/displayProducts";
    }
    @GetMapping("/deleteProduct")
	public String confirm(@RequestParam String productCode, Model model) {
        Product p = productRepo.getProduct(productCode);
        System.out.println("Product will be deleted: "+p.getCode());
        model.addAttribute("product", p);
		return "deleteProduct";
	}
    @GetMapping("/confirm")
    public String deleteProduct(@RequestParam String productCode,Model model){
        System.out.println("Product deleted: "+productCode);
        productRepo.deleteProduct(productCode);
        return "redirect:/productMaint1/displayProducts";
    }
    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam String productCode,Model model){
        model.addAttribute("product", productRepo.getProduct(productCode));
        return "updateProduct";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(Product product){
        productRepo.update(product);
        return "redirect:/productMaint1/displayProducts";
    }
}
