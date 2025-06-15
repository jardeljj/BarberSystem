package com.barber.BarberSystem.model;

import jakarta.persistence.*;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer quantity;

}
