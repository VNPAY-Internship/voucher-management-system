package com.vnpay.vouchersystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Getter
@Setter @NoArgsConstructor
@Table(name = "campaign", schema = "voucher_app")
public class CampaignEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "authorized_roles", columnDefinition = "TEXT[]")
    private String[] authorizedRoles;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "status")
    private String status;

    @Column(name = "budget")
    private Double budget;


    public CampaignEntity(Long id, String name, String description, Date startDate, Date endDate, String[] authorizedRoles, Date createdAt, Date updatedAt, String createdBy, String updatedBy, String status, Double budget) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.authorizedRoles = authorizedRoles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.status = status;
        this.budget = budget;
    }
}
