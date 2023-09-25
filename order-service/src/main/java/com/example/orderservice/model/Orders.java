package com.example.orderservice.model;

import com.example.orderservice.common.model.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private boolean status = false;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy="orders")
    @JsonIgnore
    private List<OrderDetailItem> orderDetailList = new ArrayList<>();
}
