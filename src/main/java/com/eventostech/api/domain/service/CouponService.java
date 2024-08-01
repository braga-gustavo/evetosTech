package com.eventostech.api.domain.service;

import com.eventostech.api.domain.DTO.CouponDTO;
import com.eventostech.api.domain.entity.coupon.Coupon;
import com.eventostech.api.domain.entity.event.Event;
import com.eventostech.api.domain.repository.CouponRepository;
import com.eventostech.api.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponsToEvent(UUID eventID, CouponDTO couponDTO) {
        Event event = eventRepository.findById(eventID).
                orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponDTO.code());
        coupon.setDiscount(couponDTO.discount());
        coupon.setValid( new Date(couponDTO.valid()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }
}
