package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Event;
import com.example.demo.domain.Subscription;
import com.example.demo.dtos.EmailRequestDTO;
import com.example.demo.dtos.EventRequestDTO;
import com.example.demo.exceptions.EventFullException;
import com.example.demo.exceptions.EventNotFoundException;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.SubscriptionRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;

    private final SubscriptionRepository subscriptionRepository;

    private final EmailServiceClient emailServiceClient;

    public EventService(EmailServiceClient emailServiceClient, EventRepository eventRepository,
            SubscriptionRepository subscriptionRepository) {
        this.emailServiceClient = emailServiceClient;
        this.eventRepository = eventRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findByDateAfterOrderByDate(LocalDateTime.now());
    }

    public Event createEvent(EventRequestDTO eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    private Boolean isEventFull(Event event) {
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(String eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if (isEventFull(event)) {
            throw new EventFullException();
        }

        Subscription subscription = new Subscription(event, participantEmail);
        subscriptionRepository.save(subscription);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

        EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail, "Confirmação de Inscrição",
                "Você foi inscrito no evento com sucesso!");

        emailServiceClient.sendEmail(emailRequest);
    }
}