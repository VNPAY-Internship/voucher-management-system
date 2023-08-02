package com.vnpay.vouchersystem.service.voucher;

import com.vnpay.vouchersystem.model.Campaign;
import com.vnpay.vouchersystem.model.Customer;
import com.vnpay.vouchersystem.model.Voucher;
import com.vnpay.vouchersystem.repository.CustomerRepository;
import com.vnpay.vouchersystem.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VoucherImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public VoucherImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public Voucher saveVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
        return voucher;
    }
    @Override
    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers = voucherRepository.findAll();

        return vouchers
                .stream()
                .map(voucher -> {
                    Campaign campaign = voucher.getCampaignId();
                    return new Voucher(
                            voucher.getId(),
                            campaign,
                            voucher.getCode(),
                            voucher.getStatus(),
                            voucher.getExpirationDate(),
                            voucher.getUsageLimits(),
                            voucher.getRestrictions(),
                            voucher.getCreatedAt(),
                            voucher.getUpdatedAt(),
                            voucher.getVoucherType(),
                            voucher.getRedeemDate(),
                            voucher.getRedeemedBy()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public Voucher getVoucherById(Long id) {
        return voucherRepository.findById(id).get();
    }

    @Override
    public boolean deleteVoucher(Long id) {
        Voucher voucher =  voucherRepository.findById(id).get();
        voucherRepository.delete(voucher);
        return true;
    }

    @Override
    public Voucher updateVoucher(Long id, Voucher voucher) {
        voucher =
                voucherRepository.findById(id).get();
        voucher.setCode(voucher.getCode());
        voucher.setUpdatedAt(voucher.getUpdatedAt());
        voucher.setVoucherType(voucher.getVoucherType());
        voucher.setCreatedAt(voucher.getCreatedAt());
        voucher.setExpirationDate(voucher.getExpirationDate());
        voucher.setRedeemDate(voucher.getRedeemDate());
        voucher.setRedeemedBy(voucher.getRedeemedBy());

        voucherRepository.save(voucher);
        return voucher;
    }
    @Override
    public List<Voucher> searchVouchers(String searchTerm) {

        // Convert VoucherEntities to Vouchers and return
        return voucherRepository.searchVouchers(searchTerm);
    }

    @Override
    public Long countRemainingVouchers() {
        return voucherRepository.countByStatus("unredeemed");
    }


    @Override
    public void sendVoucherToCustomer(Long customerId, Long voucherId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Voucher voucher = voucherRepository.findById(voucherId)
                .orElseThrow(() -> new RuntimeException("Voucher not found"));

        customer.getVouchers().add(voucher); // Adds the voucher to the customer's vouchers.
        customer.setNumberOfVouchersReceived(customer.getNumberOfVouchersReceived() + 1); // Increments the voucher received count.

        customerRepository.save(customer);
    }
}
