package com.example.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Event;

import jakarta.annotation.Nonnull;

public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "SELECT * FROM events e WHERE parsedatetime(e.date, 'dd/MM/yyyy') > :currentDate", nativeQuery = true)
    List<Event> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);

    public List<Event> findByDateAfterOrderByDate(LocalDateTime date);

    @Nonnull
    @Override
    Optional<Event> findById(@Nonnull String id);
}
