package com.eventostech.api.domain.entity.coupon;

import com.eventostech.api.domain.entity.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "coupon")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Coupon {

    @Id
    @GeneratedValue
    private UUID id;

    private Integer discount;

    @Column(name="coupon")
    private String couponCode;

    private Date valid;

   @ManyToOne
   @JoinColumn(name = "event_id")
    private Event event;
}
