package com.example.producingwebservice.service.impl;

import com.bialystok.event.ws.Event;
import com.bialystok.event.ws.GetAllEventsRequest;
import com.example.producingwebservice.repository.EventRepository;
import com.example.producingwebservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public GetAllEventsRequest getAllEvents() {
        GetAllEventsRequest response = new GetAllEventsRequest();
        List<Event> events = eventRepository.findAll();
        response.getEventList().addAll(events);

        return response;
    }
}
