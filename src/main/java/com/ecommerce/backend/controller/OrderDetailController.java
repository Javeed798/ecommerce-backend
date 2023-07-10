package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.OrderInput;
import com.ecommerce.backend.entity.OrderProductQuantity;
import com.ecommerce.backend.service.impl.OrderDetailService;
import com.ecommerce.backend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/placeOrder/{isSingleProductCheckout}")
    public void placeOrder(@PathVariable boolean isSingleProductCheckout,
                           @RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput, isSingleProductCheckout);
    }
}
