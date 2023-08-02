package com.vnpay.vouchersystem.controller;

import com.vnpay.vouchersystem.model.Customer;
import com.vnpay.vouchersystem.model.Voucher;
import com.vnpay.vouchersystem.service.KafkaProducerService;
import com.vnpay.vouchersystem.service.voucher.VoucherService;
import com.vnpay.vouchersystem.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private KafkaProducerService kafkaProducerService;

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

    @GetMapping("/vouchers/search")
    public List<Voucher> searchVouchers(@RequestParam("term") String searchTerm) {
        return voucherService.searchVouchers(searchTerm);
    }


    @GetMapping("/vouchers/count")
    public Long countRemainingVouchers() {
        return voucherService.countRemainingVouchers();
    }


    @GetMapping("/satisfied-customers")
    public ResponseEntity<Set<Customer>> getSatisfiedCustomers() {
        Set<Customer> customers = customerService.findSatisfiedCustomers();
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/send-voucher")
    public ResponseEntity<?> sendVoucherToCustomer(@RequestParam Long customerId, @RequestParam Long voucherId) {
        voucherService.sendVoucherToCustomer(customerId, voucherId);
        return ResponseEntity.ok("Voucher sent successfully");
    }

    @PostMapping("/redeem-voucher")
    public ResponseEntity<?> redeemVoucher(@RequestParam Long customerId, @RequestParam Long voucherId) {
        kafkaProducerService.sendRedemptionRequest(customerId, voucherId);
        return ResponseEntity.ok("Voucher redemption request sent");
    }
}
