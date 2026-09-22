package com.example.demo.domain;

import com.example.demo.dtos.EventRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "event")
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private int maxParticipants;
    private int registeredParticipants;
    private String date;
    private String title;
    private String description;

    public Event(EventRequestDTO eventRequestDTO) {
        this.maxParticipants = eventRequestDTO.maxParticipants();
        this.registeredParticipants = eventRequestDTO.registeredParticipants();
        this.date = eventRequestDTO.date();
        this.title = eventRequestDTO.title();
        this.description = eventRequestDTO.description();
    }
}