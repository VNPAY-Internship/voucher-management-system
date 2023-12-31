package com.vnpay.vouchersystem.controller;

import com.vnpay.vouchersystem.model.Voucher;
import com.vnpay.vouchersystem.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class VoucherController {

    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @PostMapping("/vouchers")
    public Voucher saveVoucher(@RequestBody Voucher voucher) {
        return voucherService.saveVoucher(voucher);
    }

    @GetMapping("/vouchers")
    public List<Voucher> getAllVouchers() {
        return voucherService.getAllVouchers();
    }

    @GetMapping("/vouchers/{id}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable("id") Long id) {
        Voucher voucher = null;
        voucher = voucherService.getVoucherById(id);
        return ResponseEntity.ok(voucher);
    }

    @DeleteMapping("/vouchers/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable("id") Long id) {
        boolean deleted = false;
        deleted =voucherService.deleteVoucher(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/vouchers/{id}")
    public ResponseEntity<Voucher> updateVoucher(@PathVariable("id") Long id,
                                           @RequestBody Voucher voucher) {
        voucher = voucherService.updateVoucher(id,voucher);
        return ResponseEntity.ok(voucher);
    }

}
