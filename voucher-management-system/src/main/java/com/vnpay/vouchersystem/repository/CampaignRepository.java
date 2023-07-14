package com.vnpay.vouchersystem.repository;

import com.vnpay.vouchersystem.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long>
    {
    }
