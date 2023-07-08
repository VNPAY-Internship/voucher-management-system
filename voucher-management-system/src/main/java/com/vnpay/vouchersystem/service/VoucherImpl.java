package com.vnpay.vouchersystem.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.vnpay.vouchersystem.entity.CampaignEntity;
import com.vnpay.vouchersystem.entity.VoucherEntity;
import com.vnpay.vouchersystem.model.Campaign;
import com.vnpay.vouchersystem.model.Voucher;
import com.vnpay.vouchersystem.repository.VoucherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherImpl implements VoucherService {

    private final VoucherRepository voucherRepository;

    public VoucherImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public Voucher saveVoucher(Voucher voucher) {
        VoucherEntity voucherEntity = new VoucherEntity();
        BeanUtils.copyProperties(voucher, voucherEntity);
        voucherRepository.save(voucherEntity);
        voucherRepository.save(voucherEntity);
        return voucher;
    }
    @Override
    public List<Voucher> getAllVouchers() {
        List<VoucherEntity> voucherEntities = voucherRepository.findAll();

        return voucherEntities
                .stream()
                .map(voucherEntity -> {
                    CampaignEntity campaignEntity = voucherEntity.getCampaignId();
                    Campaign campaign = new Campaign(campaignEntity);  // Create a Campaign object using the CampaignEntity
                    return new Voucher(
                            voucherEntity.getId(),
                            campaign,  // Pass the Campaign object instead of CampaignEntity
                            voucherEntity.getCode(),
                            voucherEntity.getStatus(),
                            voucherEntity.getExpirationDate(),
                            voucherEntity.getUsageLimits(),
                            voucherEntity.getRestrictions(),
                            voucherEntity.getCreatedAt(),
                            voucherEntity.getUpdatedAt(),
                            voucherEntity.getVoucherType(),
                            voucherEntity.getRedeemDate(),
                            voucherEntity.getRedeemedBy()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public Voucher getVoucherById(Long id) {
        VoucherEntity voucherEntity
                = voucherRepository.findById(id).get();
        Voucher voucher = new Voucher();
        BeanUtils.copyProperties(voucherEntity, voucher);
        return voucher;
    }

    @Override
    public boolean deleteVoucher(Long id) {
        VoucherEntity voucher =  voucherRepository.findById(id).get();
        voucherRepository.delete(voucher);
        return true;
    }

    @Override
    public Voucher updateVoucher(Long id, Voucher voucher) {
        VoucherEntity voucherEntity =
                voucherRepository.findById(id).get();
        voucherEntity.setCode(voucher.getCode());
        voucherEntity.setUpdatedAt(voucher.getUpdatedAt());
        voucherEntity.setVoucherType(voucher.getVoucherType());
        voucherEntity.setCreatedAt(voucher.getCreatedAt());
        voucherEntity.setExpirationDate(voucher.getExpirationDate());
        voucherEntity.setRedeemDate(voucher.getRedeemDate());
        voucherEntity.setRedeemedBy(voucher.getRedeemedBy());

        voucherRepository.save(voucherEntity);
        return voucher;
    }

}
