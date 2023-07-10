package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.configuration.JwtRequestFilter;
import com.ecommerce.backend.dao.CartDao;
import com.ecommerce.backend.dao.ProductDao;
import com.ecommerce.backend.dao.UserDao;
import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId){
        Product product = productDao.findById(productId).get();

        String username = JwtRequestFilter.CURRENT_USER;
        User user = null;
        if(username != null) {
             user = userDao.findById(username).get();
        }

        if(product != null && user != null){
            Cart cart = new Cart(
                    product,user
            );
            return cartDao.save(cart);
        }
        return null;
    }
}
