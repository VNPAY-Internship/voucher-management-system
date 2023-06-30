package com.vnpay.vouchersystem.repository;

import com.vnpay.vouchersystem.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {

}
