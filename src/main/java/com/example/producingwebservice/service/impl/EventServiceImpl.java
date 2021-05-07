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
    public GetAllEventsResponse getAllEvents() {
        GetAllEventsResponse response = new GetAllEventsResponse();
        List<Event> events = eventRepository.findAll();
        response.getEventList().addAll(events);

        return response;
    }

    @Override
    public GetEventsByDateResponse getEventsByDate(XMLGregorianCalendar date) {
        GetEventsByDateResponse response = new GetEventsByDateResponse();
        List<Event> events = eventRepository.findByDate(date);
        response.getEventList().addAll(events);
        return response;
    }

    @Override
    public GetEventsByWeekResponse getEventsByWeek(Integer week) {
        GetEventsByWeekResponse response = new GetEventsByWeekResponse();
        List<Event> events = eventRepository.findByWeek(week);
        response.getEventList().addAll(events);
        return response;
    }

    @Override
    public GetEventDetailsByIdResponse getEventById(Integer id) {
        GetEventDetailsByIdResponse response = new GetEventDetailsByIdResponse();
        response.setEventDetails(eventRepository.findById(id));
        return response;
    }

    @Override
    public AddEventResponse addEvent(Event event) {
        AddEventResponse response = new AddEventResponse();
        Event saved = eventRepository.add(event);
        response.setEventDetails(saved);
        return response;
    }

    @Override
    public ModifyEventResponse modifyEvent(Event event) {
        ModifyEventResponse response = new ModifyEventResponse();
        Event modified = eventRepository.update(event);
        response.setEventDetails(modified);
        return response;
    }

    @Override
    public DeleteEventResponse deleteEvent(Integer eventId) {
        DeleteEventResponse response = new DeleteEventResponse();
        Event deleted = eventRepository.delete(eventId);
        if (deleted == null) {
            response.setStatus(OpStatusCode.FAULT);
        } else {
            response.setStatus(OpStatusCode.OK);
        }
        return response;
    }

    @Override
    public GenerateEventsPDFResponse generateEventsListAsPDF() {
        File pdf = pdfCreator.create(getAllEvents().getEventList());

        GenerateEventsPDFResponse response = new GenerateEventsPDFResponse();
        FileDataSource fileDataSource = new FileDataSource(pdf);
        DataHandler dataHandler = new DataHandler(fileDataSource);
        response.setContent(dataHandler);
        return response;
    }
}
