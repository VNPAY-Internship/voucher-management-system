package com.vnpay.vouchersystem.model;

import com.vnpay.vouchersystem.entity.CampaignEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Voucher {
    private Long id;
    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaignId;
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "usage_limits")
    private Integer usageLimits;

    @Column(name = "restrictions", columnDefinition = "TEXT[]")
    private String[] restrictions;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "voucher_type", nullable = false)
    private String voucherType;

    @Column(name = "redeem_date")
    private Date redeemDate;

    @Column(name = "redeemed_by")
    private String redeemedBy;

    public Voucher(Long id, Campaign campaignId, String code, String status, Date expirationDate, Integer usageLimits, String[] restrictions, Date createdAt, Date updatedAt, String voucherType, Date redeemDate, String redeemedBy) {
        this.id = id;
        this.campaignId = campaignId;
        this.code = code;
        this.status = status;
        this.expirationDate = expirationDate;
        this.usageLimits = usageLimits;
        this.restrictions = restrictions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.voucherType = voucherType;
        this.redeemDate = redeemDate;
        this.redeemedBy = redeemedBy;
    }

    public Voucher() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Campaign getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Campaign campaignId) {
        this.campaignId = campaignId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getUsageLimits() {
        return usageLimits;
    }

    public void setUsageLimits(Integer usageLimits) {
        this.usageLimits = usageLimits;
    }

    public String[] getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String[] restrictions) {
        this.restrictions = restrictions;
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

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public Date getRedeemDate() {
        return redeemDate;
    }

    public void setRedeemDate(Date redeemDate) {
        this.redeemDate = redeemDate;
    }

    public String getRedeemedBy() {
        return redeemedBy;
    }

    public void setRedeemedBy(String redeemedBy) {
        this.redeemedBy = redeemedBy;
    }
}
