package com.eventostech.api.domain.entity.address;

import com.eventostech.api.domain.entity.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "address")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    private String uf;

    private String street;

    @OneToOne
    private Event event;


}