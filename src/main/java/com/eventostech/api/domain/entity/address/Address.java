package com.eventostech.api.domain.entity.address;

import com.eventostech.api.domain.entity.event.Event;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "address")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    private String state;

    private String city;

    @OneToOne
    @JoinColumn(name ="event_id")
    private Event event;


}