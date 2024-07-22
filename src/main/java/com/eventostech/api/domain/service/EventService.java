package com.eventostech.api.domain.service;

import com.eventostech.api.domain.DTO.EventDTO;
import com.eventostech.api.domain.entity.event.Event;
import com.eventostech.api.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;


    public Event addEvent(EventDTO eventData) {
        String imgUrl = null;

        if (eventData.image() != null) {
            imgUrl = this.uploadImg(eventData.image());

        }

        Event event = new Event();
        event.setTitle(eventData.title());
        event.setDescription(eventData.description());
        event.setEnventUrl(eventData.eventUrl());
        event.setDate(new Date(eventData.date()));
        event.setImgUrl(imgUrl);

        return new Event();
    }

    private String uploadImg(MultipartFile image) {
        return "";
    }
}
