package com.example.orderservice.repository;

import com.example.orderservice.model.OrderDetailItem;
import com.example.orderservice.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailItemRepository extends JpaRepository<OrderDetailItem, Long> {
  boolean existsByProductIdAndOrders(Long productId, Orders orders);
  OrderDetailItem findByProductIdAndOrders(Long productId,Orders orders);
}
