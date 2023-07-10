package com.ecommerce.backend.dao;

import com.ecommerce.backend.entity.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartDao extends CrudRepository<Cart,Integer> {
}
