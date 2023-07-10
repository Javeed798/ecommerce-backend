package com.ecommerce.backend.dao;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartDao extends CrudRepository<Cart,Integer> {

    public List<Cart> findByUser(User user);

}
