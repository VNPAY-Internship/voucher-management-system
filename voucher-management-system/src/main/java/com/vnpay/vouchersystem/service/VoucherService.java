package com.vnpay.vouchersystem.service;

import com.vnpay.vouchersystem.model.Voucher;

import java.util.List;

public interface VoucherService {

    Voucher saveVoucher(Voucher voucher);

    List<Voucher> getAllVouchers();

    Voucher getVoucherById(Long id);

    boolean deleteVoucher(Long id);

    Voucher updateVoucher(Long id, Voucher user);

    List<Voucher> searchVouchers(String searchTerm);

    Long countRemainingVouchers();

}

