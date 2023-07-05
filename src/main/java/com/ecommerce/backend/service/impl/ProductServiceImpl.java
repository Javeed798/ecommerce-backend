package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.dao.ProductDao;
import com.ecommerce.backend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product){
        return this.productDao.save(product);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) this.productDao.findAll();
    }

    public void deleteProduct(Integer productId) {
       this.productDao.deleteById(productId);
    }

    public Product getProductDetailsById(Integer productId) {
        return this.productDao.findById(productId).get();
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if (isSingleProductCheckout) {
//            buy single product
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        } else {
//            we are going to checkout entire cart
        }
        return new ArrayList<>();
    }
}
