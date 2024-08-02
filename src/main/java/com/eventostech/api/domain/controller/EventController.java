package com.eventostech.api.domain.controller;

import com.eventostech.api.domain.DTO.EventDTO;
import com.eventostech.api.domain.DTO.EventResponseDTO;
import com.eventostech.api.domain.entity.event.Event;
import com.eventostech.api.domain.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> createEvent(@RequestParam("title") String title,
                                             @RequestParam(value = "description", required = false) String description,
                                             @RequestParam("date") Long date,
                                             @RequestParam("city") String city,
                                             @RequestParam("state") String state,
                                             @RequestParam("remote") Boolean remote,
                                             @RequestParam("eventUrl") String eventUrl,
                                             @RequestParam(value = "image", required = false) MultipartFile image) {
        EventDTO requestDto = new EventDTO(title, description, date, city, state, remote, eventUrl, image);
        Event eventToInsert = this.eventService.addEvent(requestDto);
        return ResponseEntity.ok(eventToInsert);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvent(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page, size);
        return ResponseEntity.ok(allEvents);

    }
}
