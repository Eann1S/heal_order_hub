package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
    private String text;
    private int serviceCost;
}
