package com.system.controller;

import com.system.model.Product;
import com.system.service.ProductService;
import jakarta.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    private Product toEntity(ProductForm form) {
        return new Product(
            form.getName(),
            form.getPrice(),
            form.getQuantity()
        );
    }
     
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.list());
        model.addAttribute("productForm", new ProductForm());
        return "products";
    }
    
    @PostMapping
    public String create(
        @Valid @ModelAttribute("productForm") ProductForm form,
        org.springframework.validation.BindingResult result,
        Model model
    ) {

        if (result.hasErrors()) {
            model.addAttribute("products", service.list());
            return "products";
        }

        Product product = toEntity(form);

        service.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = service.findById(id);
        model.addAttribute("productForm", product);
        model.addAttribute("products", service.list());
        return "products";
}

    @PostMapping("/update/{id}")
    public String update(
        @PathVariable Long id,
        @RequestParam String name,
        @RequestParam double price,
        @RequestParam int quantity
    ) {
        Product product = service.findById(id);

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        service.save(product);

        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/products";
    }
}