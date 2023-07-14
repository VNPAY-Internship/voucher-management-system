package com.vnpay.vouchersystem.repository;

import com.vnpay.vouchersystem.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @Query("SELECT v FROM Voucher v WHERE LOWER(v.code) LIKE LOWER(CONCAT('%',:searchTerm,'%'))")
    List<Voucher> searchVouchers(@Param("searchTerm") String searchTerm);

    @Query("SELECT COUNT(v) FROM Voucher v WHERE v.status = :status")
    Long countByStatus(@Param("status") String status);

}

