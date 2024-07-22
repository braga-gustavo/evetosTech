package com.eventostech.api.domain.DTO;

import org.springframework.web.multipart.MultipartFile;

public record EventDTO(String title, String description, Long date, String city, String uf, Boolean remote,
                       String eventUrl, MultipartFile image) {

}
