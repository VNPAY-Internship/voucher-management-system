package com.vnpay.vouchersystem.repository;

import com.vnpay.vouchersystem.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {
    @Query("SELECT v FROM VoucherEntity v WHERE LOWER(v.code) LIKE LOWER(CONCAT('%',:searchTerm,'%'))")
    List<VoucherEntity> searchVouchers(@Param("searchTerm") String searchTerm);

    @Query("SELECT COUNT(v) FROM VoucherEntity v WHERE v.status = :status")
    Long countByStatus(@Param("status") String status);

}

