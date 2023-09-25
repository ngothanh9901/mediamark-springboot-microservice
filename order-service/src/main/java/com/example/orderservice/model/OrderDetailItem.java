package com.example.orderservice.model;

import com.example.orderservice.common.model.UserDateAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetailItem extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Long quantity;

    private String note;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Orders orders;

    private Long productId;
}
