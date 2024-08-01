package com.eventostech.api.domain.controller;

import com.eventostech.api.domain.DTO.CouponDTO;
import com.eventostech.api.domain.entity.coupon.Coupon;
import com.eventostech.api.domain.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;


    public ResponseEntity<Coupon> addCouponsToEvent(@PathVariable UUID eventID, @RequestBody CouponDTO couponDTO){
        Coupon coupons = couponService.addCouponsToEvent(eventID, couponDTO);
        return ResponseEntity.ok(coupons);
    }

}
