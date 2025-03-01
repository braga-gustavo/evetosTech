package com.eventostech.api.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.eventostech.api.domain.DTO.EventDTO;
import com.eventostech.api.domain.DTO.EventResponseDTO;
import com.eventostech.api.domain.entity.event.Event;
import com.eventostech.api.domain.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AddressService addressService;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public Event addEvent(EventDTO eventData) {
        String imgUrl = null;

        if (eventData.image() != null) {
            imgUrl = this.uploadImg(eventData.image());

        }

        Event newEvent = new Event();
        newEvent.setTitle(eventData.title());
        newEvent.setDescription(eventData.description());
        newEvent.setEnventUrl(eventData.eventUrl());
        newEvent.setDate(new Date(eventData.date()));
        newEvent.setImgUrl(imgUrl);
        newEvent.setRemote(eventData.remote());

        eventRepository.save(newEvent);

        if(!eventData.remote()) {
            this.addressService.createAddress(eventData, newEvent);
        }

        return newEvent;
    }

    private String uploadImg(MultipartFile multipartFile) {
        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            File file = this.convertMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, imgName, file);
            file.delete();
            return s3Client.getUrl(bucketName, imgName).toString();
        } catch (Exception e) {
            System.out.println("Image upload error: " + e.getMessage());
            return "";
        }
    }

    private File convertMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }

    public List<EventResponseDTO> getUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = eventRepository.getUpcomingEvents(new Date(), pageable);
        return getEventResponseDTOS(eventsPage);

    }

    public List<EventResponseDTO> getFileteredEvents(int page, int size, String title, String city, String uf,
                                                     Date starDate,
                                                     Date endDate) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> eventsPage = eventRepository.findFilteredEvents(new Date(),city, title, uf, starDate, endDate, pageable);
        return getEventResponseDTOS(eventsPage);
    }

    private List<EventResponseDTO> getEventResponseDTOS(Page<Event> eventsPage) {
        return eventsPage.map(event -> new EventResponseDTO(event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                event.getAddress() != null ?  event.getAddress().getCity() : "" ,
                event.getAddress() != null ?  event.getAddress().getState() : "" ,
                event.getRemote(),
                event.getEnventUrl(),
                event.getImgUrl())).toList();
    }
}
