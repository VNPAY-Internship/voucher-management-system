package com.vnpay.vouchersystem.repository;

import com.vnpay.vouchersystem.entity.CampaignEntity;
import com.vnpay.vouchersystem.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<CampaignEntity, Long>
    {
    }
