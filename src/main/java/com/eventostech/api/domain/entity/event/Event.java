package com.eventostech.api.domain.entity.event;


import com.eventostech.api.domain.entity.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    private Date date;

    private Boolean remote;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "event_url")
    private String enventUrl;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;

}



