package com.vnpay.vouchersystem.service;

import com.vnpay.vouchersystem.model.Campaign;

import java.util.List;

public interface CampaignService {

    Campaign saveCampaign(Campaign campaign);

    List<Campaign> getAllCampaigns();

    Campaign getCampaignById(Long id);

    boolean deleteCampaign(Long id);

    Campaign updateCampaign(Long id, Campaign user);
}

