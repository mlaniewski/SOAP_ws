package com.example.producingwebservice.service.impl;

import com.bialystok.event.ws.*;
import com.example.producingwebservice.repository.EventRepository;
import com.example.producingwebservice.service.EventService;
import com.example.producingwebservice.service.PDFCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PDFCreator pdfCreator;

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

    @Override
    public EventDetailsResponse addEvent(EventDto eventDto) {
        EventDetailsResponse response = new EventDetailsResponse();
        Event saved = eventRepository.add(eventDto);
        response.setEventDetails(saved);
        return response;
    }

    @Override
    public EventsListPDFResponse generateEventsListAsPDF() {
        File pdf = pdfCreator.create(getAllEvents().getEventList());

        EventsListPDFResponse response = new EventsListPDFResponse();
        FileDataSource fileDataSource = new FileDataSource(pdf);
        DataHandler dataHandler = new DataHandler(fileDataSource);
        response.setContent(dataHandler);
        return response;
    }
}
