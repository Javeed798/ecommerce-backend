package com.ecommerce.backend.dao;

import com.ecommerce.backend.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
}
