package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CartController {


    @Autowired
    private CartServiceImpl cartService;

    @PreAuthorize("hasRole('User')")
    @GetMapping ("/addToCart/{productId}")
    public Cart addToCart(@PathVariable(name = "productId") Integer productId){
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/getCartDetails")
    public List<Cart> getCartDetails(){
        return cartService.getCartDetails();
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping("/deleteCartItem/{cartId}")
    public void deleteCartItem(@PathVariable Integer cartId) {
        cartService.deleteCartItem(cartId);
    }

}
