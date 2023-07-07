package com.ecommerce.backend.controller;

import com.ecommerce.backend.dao.ProductDao;
import com.ecommerce.backend.entity.ImageModel;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = "/addNewProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] file
    ) {
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int pageNumber) {
       return this.productService.getAllProducts(pageNumber);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/deleteProduct/{productId}")
    public void deleteProduct(@PathVariable Integer productId){
        this.productService.deleteProduct(productId);
    }


    @GetMapping("/getProductDetailsById/{productId}")
    public Product getProductDetailsById(@PathVariable Integer productId){
        return this.productService.getProductDetailsById(productId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/getProductDetails/{isSingleProductCheckout}/{productId}")
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
                                           @PathVariable(name = "productId") Integer productId) {
       return  productService.getProductDetails(isSingleProductCheckout,productId);
    }
}
