package com.eventostech.api.domain.entity.event;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Table(name = "event")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private Date date;

    @Column(name = "remote")
    private Boolean isRemote;

    private String imgUrl;

    private String enventUrl;

}