package com.eventostech.api.domain.entity.event;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;


@Table(name = "event")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private LocalDate date;

    @Column(name = "remote")
    private Boolean isRemote;

    @Column(name = "img")
    private String img_url;

    @Column(name = "event")
    private String envent_url;

}