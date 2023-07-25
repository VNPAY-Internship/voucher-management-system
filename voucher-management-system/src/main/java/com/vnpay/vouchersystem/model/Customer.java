package com.vnpay.vouchersystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int numberOfVouchersRedeemed;
    private int numberOfVouchersReceived;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_campaigns",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "campaign_id"))
    private Set<Campaign> participatedCampaigns = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "customer_vouchers",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "voucher_id"))
    private Set<Voucher> vouchers = new HashSet<>();

    public Customer(User user) {
        this.user = user;
        this.numberOfVouchersRedeemed = 0;
        this.numberOfVouchersReceived = 0;
    }
}

