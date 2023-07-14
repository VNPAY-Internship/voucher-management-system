package com.vnpay.vouchersystem.service;

import com.vnpay.vouchersystem.model.Campaign;
import com.vnpay.vouchersystem.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampaignImpl implements CampaignService {

    private final CampaignRepository campaignRepository;

    public CampaignImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign saveCampaign(Campaign campaign) {
        campaignRepository.save(campaign);
        return campaign;
    }
    @Override
    public List<Campaign> getAllCampaigns() {
        List<Campaign> campaigns
                = campaignRepository.findAll();

        return campaigns
                .stream()
                .map(campaign -> new Campaign(
                        campaign.getId(),
                        campaign.getName(),
                        campaign.getDescription(),
                        campaign.getStartDate(),
                        campaign.getEndDate(),
                        campaign.getAuthorizedRoles(),
                        campaign.getCreatedAt(),
                        campaign.getUpdatedAt(),
                        campaign.getCreatedBy(),
                        campaign.getUpdatedBy(),
                        campaign.getStatus(),
                        campaign.getBudget()
                                ))
                .collect(Collectors.toList());
    }

    @Override
    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id).get();
    }

    @Override
    public boolean deleteCampaign(Long id) {
        Campaign campaign =  campaignRepository.findById(id).get();
        campaignRepository.delete(campaign);
        return true;
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaign) {
        campaign =
                campaignRepository.findById(id).get();
        campaign.setName(campaign.getName());
        campaign.setDescription(campaign.getDescription());
        campaign.setStartDate(campaign.getStartDate());
        campaign.setEndDate(campaign.getEndDate());
        campaign.setAuthorizedRoles(campaign.getAuthorizedRoles());
        campaign.setCreatedAt(campaign.getCreatedAt());
        campaign.setUpdatedAt(campaign.getUpdatedAt());
        campaign.setCreatedBy(campaign.getCreatedBy());
        campaign.setUpdatedBy(campaign.getUpdatedBy());
        campaign.setStatus(campaign.getStatus());
        campaign.setBudget(campaign.getBudget());
        campaignRepository.save(campaign);
        return campaign;
    }
}
