package com.eventostech.api.domain.repository;

import com.eventostech.api.domain.entity.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.date >= :currentDate")
    public Page<Event> getUpcomingEvents(@Param("currentDate") Date currentDate, Pageable page);


    @Query("SELECT e FROM Event e LEFT JOIN e.address a WHERE e.date >= :currentDate +" +
            "AND (:title IS NULL OR e.title LIKE %:title%)" +
            "AND (:ctiy IS NULL OR e.city LIKE %:title%)" +
            "AND (:uf IS NULL OR e.uf LIKE %:uf%)" +
            "AND (:starDate IS NULL OR e.startDate >= :startDate)" +
            "AND (:endDate IS NULL OR e.endDate <= :endDate)")
    public Page<Event> findFilteredEvents(@Param("currentDate") Date currentDate,
                                          @Param("title") String title,
                                          @Param("city") String city,
                                          @Param("uf") String uf,
                                          @Param("startDate") Date startDate,
                                          @Param("endDate") Date endDate,
                                          Pageable page);
}
