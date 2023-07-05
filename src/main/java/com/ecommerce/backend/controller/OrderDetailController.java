package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.OrderInput;
import com.ecommerce.backend.entity.OrderProductQuantity;
import com.ecommerce.backend.service.impl.OrderDetailService;
import com.ecommerce.backend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput);
    }
}
