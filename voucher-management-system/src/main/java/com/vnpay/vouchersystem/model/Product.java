package com.vnpay.vouchersystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToMany(mappedBy = "associatedProducts")
    private Set<Campaign> campaigns = new HashSet<>();

    // This assumes a product can have multiple vouchers.
    // If a product can only be associated with one voucher, change this to @ManyToOne.
    @ManyToMany
    private Set<Voucher> vouchers = new HashSet<>();

}
