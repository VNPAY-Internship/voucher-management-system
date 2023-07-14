package com.vnpay.vouchersystem.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "voucher", schema = "voucher_app")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
