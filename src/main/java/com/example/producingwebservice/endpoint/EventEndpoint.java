package com.example.producingwebservice.endpoint;

import com.bialystok.event.ws.*;
import com.example.producingwebservice.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

@Endpoint
public class EventEndpoint {
    private static final String NAMESPACE_URI = "http://ws.event.bialystok.com";

    @Autowired
    private EventServiceImpl eventService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEventsRequest")
    @ResponsePayload
    public EventsListResponse getAllEvents() {
        return eventService.getAllEvents();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsByDateRequest")
    @ResponsePayload
    public EventsListResponse getEventsByDate(@RequestPayload GetEventsByDateRequest request) {
        return eventService.getEventsByDate(request.getDate());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsByWeekRequest")
    @ResponsePayload
    public EventsListResponse getEventsByWeek(@RequestPayload GetEventsByWeekRequest request) {
        return eventService.getEventsByWeek(request.getWeek());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventDetailsByIdRequest")
    @ResponsePayload
    public EventDetailsResponse getEventsByWeek(@RequestPayload GetEventDetailsByIdRequest request) {
        return eventService.getEventById(request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEventRequest")
    @ResponsePayload
    public EventDetailsResponse addEvent(@RequestPayload AddEventRequest request) {
        return eventService.addEvent(request.getEventDto());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "generateEventsPDFRequest")
    @ResponsePayload
    public EventsListPDFResponse generateEventsPDF(@RequestPayload GenerateEventsPDFRequest request) {
        EventsListPDFResponse response = new EventsListPDFResponse();
        FileDataSource fileDataSource = new FileDataSource("/home/mateusz/Pobrane/patrik.png");
        DataHandler dataHandler = new DataHandler(fileDataSource);
        response.setContent(dataHandler);

        return response;
    }
}