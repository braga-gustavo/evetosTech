package com.eventostech.api.domain.service;

import com.eventostech.api.domain.entity.event.Event;
import com.eventostech.api.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;


    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }
}
