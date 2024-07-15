package com.eventostech.api.domain.repository;

import com.eventostech.api.domain.entity.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
