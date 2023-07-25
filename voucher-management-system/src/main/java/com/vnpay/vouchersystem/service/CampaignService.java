package com.vnpay.vouchersystem.service;

import com.vnpay.vouchersystem.model.Campaign;
import com.vnpay.vouchersystem.model.Product;

import java.util.List;
import java.util.Set;

public interface CampaignService {

    Campaign saveCampaign(Campaign campaign);

    List<Campaign> getAllCampaigns();

    Campaign getCampaignById(Long id);

    boolean deleteCampaign(Long id);

    Campaign updateCampaign(Long id, Campaign user);

    Set<Product> findProductsByCampaignId(Long id);
}

