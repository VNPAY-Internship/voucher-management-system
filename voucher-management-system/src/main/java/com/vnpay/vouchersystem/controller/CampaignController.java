package com.vnpay.vouchersystem.controller;

import com.vnpay.vouchersystem.model.Campaign;
import com.vnpay.vouchersystem.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CampaignController {

    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping("/campaigns")
    public Campaign saveCampaign(@RequestBody Campaign campaign) {
        return campaignService.saveCampaign(campaign);
    }

    @GetMapping("/campaigns")
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable("id") Long id) {
        Campaign campaign = null;
        campaign = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaign);
    }

    @DeleteMapping("/campaigns/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable("id") Long id) {
        boolean deleted = false;
        deleted =campaignService.deleteCampaign(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/campaigns/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable("id") Long id,
                                                 @RequestBody Campaign campaign) {
        campaign = campaignService.updateCampaign(id,campaign);
        return ResponseEntity.ok(campaign);
    }
}
