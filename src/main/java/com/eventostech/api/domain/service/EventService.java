package com.eventostech.api.domain.service;

import com.amazonaws.services.s3.AmazonS3;
import com.eventostech.api.domain.DTO.EventDTO;
import com.eventostech.api.domain.entity.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private AmazonS3 s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

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

    private String uploadImg(MultipartFile multipartFile) {
        String imgName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            File file = this.converMultipartToFile(multipartFile);
            s3Client.putObject(bucketName, imgName, file);
            file.delete();
            return s3Client.getUrl(bucketName, imgName).toString();
        } catch (Exception e) {
            System.out.println("Image upload error: " + e.getMessage());
            return null;
        }
    }

    private File converMultipartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}
