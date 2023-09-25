package com.example.orderservice.repository;

import com.example.orderservice.model.OrderDetailItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailItemRepository extends JpaRepository<OrderDetailItem,Long> {
}
