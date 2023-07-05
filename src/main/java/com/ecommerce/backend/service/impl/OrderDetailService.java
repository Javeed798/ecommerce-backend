package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.configuration.JwtRequestFilter;
import com.ecommerce.backend.dao.OrderDetailDao;
import com.ecommerce.backend.dao.ProductDao;
import com.ecommerce.backend.dao.UserDao;
import com.ecommerce.backend.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

//    here we need to use the orderInput to fetch the data and by using the main entity table OrderDetail we will save the data into the database
//    Here the orderInput is only like a helper to take the data and save it
    public void placeOrder(OrderInput orderInput) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
        for (OrderProductQuantity o : productQuantityList) {
            Product product = productDao.findById(o.getProductId()).get();
            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();
            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user
            );
            orderDetailDao.save(orderDetail);
        }
    }
}
