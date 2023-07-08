package com.vnpay.vouchersystem.service;

import com.vnpay.vouchersystem.entity.CampaignEntity;
import com.vnpay.vouchersystem.model.Campaign;
import com.vnpay.vouchersystem.repository.CampaignRepository;
import org.springframework.beans.BeanUtils;
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
        CampaignEntity campaignEntity = new CampaignEntity();
        BeanUtils.copyProperties(campaign, campaignEntity);
        campaignRepository.save(campaignEntity);
        campaignRepository.save(campaignEntity);
        return campaign;
    }
    @Override
    public List<Campaign> getAllCampaigns() {
        List<CampaignEntity> campaignEntities
                = campaignRepository.findAll();

        return campaignEntities
                .stream()
                .map(campaignEntity -> new Campaign(
                        campaignEntity.getId(),
                        campaignEntity.getName(),
                        campaignEntity.getDescription(),
                        campaignEntity.getStartDate(),
                        campaignEntity.getEndDate(),
                        campaignEntity.getAuthorizedRoles(),
                        campaignEntity.getCreatedAt(),
                        campaignEntity.getUpdatedAt(),
                        campaignEntity.getCreatedBy(),
                        campaignEntity.getUpdatedBy(),
                        campaignEntity.getStatus(),
                        campaignEntity.getBudget()
                                ))
                .collect(Collectors.toList());
    }

    @Override
    public Campaign getCampaignById(Long id) {
        CampaignEntity campaignEntity
                = campaignRepository.findById(id).get();
        Campaign campaign = new Campaign();
        BeanUtils.copyProperties(campaignEntity, campaign);
        return campaign;
    }

    @Override
    public boolean deleteCampaign(Long id) {
        CampaignEntity campaign =  campaignRepository.findById(id).get();
        campaignRepository.delete(campaign);
        return true;
    }

    @Override
    public Campaign updateCampaign(Long id, Campaign campaign) {
        CampaignEntity campaignEntity =
                campaignRepository.findById(id).get();
        campaignEntity.setName(campaign.getName());
        campaignEntity.setDescription(campaign.getDescription());
        campaignEntity.setStartDate(campaign.getStartDate());
        campaignEntity.setEndDate(campaign.getEndDate());
        campaignEntity.setAuthorizedRoles(campaign.getAuthorizedRoles());
        campaignEntity.setCreatedAt(campaign.getCreatedAt());
        campaignEntity.setUpdatedAt(campaign.getUpdatedAt());
        campaignEntity.setCreatedBy(campaign.getCreatedBy());
        campaignEntity.setUpdatedBy(campaign.getUpdatedBy());
        campaignEntity.setStatus(campaign.getStatus());
        campaignEntity.setBudget(campaign.getBudget());
        campaignRepository.save(campaignEntity);
        return campaign;
    }



}
