package com.eventostech.api.domain.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record EventDTO(String title, String description, Date data, String city, String uf, Boolean remote,
                       String eventUrl, MultipartFile image) {

}
