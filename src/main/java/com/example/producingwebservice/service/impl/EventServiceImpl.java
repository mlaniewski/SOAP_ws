package com.example.producingwebservice.service.impl;

import com.bialystok.event.ws.Event;
import com.bialystok.event.ws.EventDetailsResponse;
import com.bialystok.event.ws.EventsListResponse;
import com.example.producingwebservice.repository.EventRepository;
import com.example.producingwebservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

@Component
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventsListResponse getAllEvents() {
        EventsListResponse response = new EventsListResponse();
        List<Event> events = eventRepository.findAll();
        response.getEventList().addAll(events);

        return response;
    }

    @Override
    public EventsListResponse getEventsByDate(XMLGregorianCalendar date) {
        EventsListResponse response = new EventsListResponse();
        List<Event> events = eventRepository.findByDate(date);
        response.getEventList().addAll(events);
        return response;
    }

    @Override
    public EventsListResponse getEventsByWeek(Integer week) {
        EventsListResponse response = new EventsListResponse();
        List<Event> events = eventRepository.findByWeek(week);
        response.getEventList().addAll(events);
        return response;
    }

    @Override
    public EventDetailsResponse getEventById(Integer id) {
        EventDetailsResponse response = new EventDetailsResponse();
        response.setEventDetails(eventRepository.findById(id));
        return response;
    }
}
