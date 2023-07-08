package com.vnpay.vouchersystem.model;

import com.vnpay.vouchersystem.entity.CampaignEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import java.util.Date;
import java.util.List;

public class Campaign {
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "authorized_roles")
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


    public Campaign(Long id, String name, String description, Date startDate, Date endDate, String[] authorizedRoles, Date createdAt, Date updatedAt, String createdBy, String updatedBy, String status, Double budget) {
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

    public Campaign() {
    }
    public Campaign(CampaignEntity campaignEntity) {
        this.id = campaignEntity.getId();
        this.name = campaignEntity.getName();
        this.description = campaignEntity.getDescription();
        this.startDate = campaignEntity.getStartDate();
        this.endDate = campaignEntity.getEndDate();
        this.authorizedRoles = campaignEntity.getAuthorizedRoles();
        this.createdAt = campaignEntity.getCreatedAt();
        this.updatedAt = campaignEntity.getUpdatedAt();
        this.createdBy = campaignEntity.getCreatedBy();
        this.updatedBy = campaignEntity.getUpdatedBy();
        this.status = campaignEntity.getStatus();
        this.budget = campaignEntity.getBudget();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String[] getAuthorizedRoles() {
        return authorizedRoles;
    }

    public void setAuthorizedRoles(String[] authorizedRoles) {
        this.authorizedRoles = authorizedRoles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}

