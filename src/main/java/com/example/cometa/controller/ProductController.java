package com.example.cometa.controller;

import com.example.cometa.domain.*;
import com.example.cometa.domain.robcom.Defect;
import com.example.cometa.domain.robcom.Product;
import com.example.cometa.repos.DefectRepo;
import com.example.cometa.repos.ProductRepo;
import com.example.cometa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    ImageService imageService;

    @Autowired
    private DefectRepo defectRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/products")
    public String products(
            @AuthenticationPrincipal User user,
            Model model){
        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("user", user);
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products")
    public String add(@RequestParam String name,
                      @RequestParam String description,
                      @RequestParam("file") MultipartFile file
                      ) throws IOException {

        Product product = imageService.addProductImage(new Product(name, description), file);
        productRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{product}")
    public String productView(@PathVariable Product product,
                              @AuthenticationPrincipal User user,
                              Model model){

        Iterable<Defect> defects = defectRepo.findByProduct(product);

        model.addAttribute("defects", defects);
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "productView";
    }

    @GetMapping("/products/delete")
    public String delete(@RequestParam (name="id", required = false) int id, Map<String, Object> model){
        if (productRepo.findById(id) != null){
            productRepo.deleteById(id);
        }
        Iterable<Product> products = productRepo.findAll();
        model.put("products", products);
        return "redirect:/products";
    }

}
