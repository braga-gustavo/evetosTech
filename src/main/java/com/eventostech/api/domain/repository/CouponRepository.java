package com.eventostech.api.domain.repository;


import com.eventostech.api.domain.entity.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
}
