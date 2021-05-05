package com.example.producingwebservice.endpoint;

import com.bialystok.event.ws.*;
import com.example.producingwebservice.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EventEndpoint {
    private static final String NAMESPACE_URI = "http://ws.event.bialystok.com";

    @Autowired
    private EventServiceImpl eventService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEventsRequest")
    @ResponsePayload
    public GetAllEventsResponse getAllEvents() {
        return eventService.getAllEvents();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsByDateRequest")
    @ResponsePayload
    public GetEventsByDateResponse getEventsByDate(@RequestPayload GetEventsByDateRequest request) {
        return eventService.getEventsByDate(request.getDate());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsByWeekRequest")
    @ResponsePayload
    public GetEventsByWeekResponse getEventsByWeek(@RequestPayload GetEventsByWeekRequest request) {
        return eventService.getEventsByWeek(request.getWeek());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventDetailsByIdRequest")
    @ResponsePayload
    public GetEventDetailsByIdResponse getEventsByWeek(@RequestPayload GetEventDetailsByIdRequest request) {
        return eventService.getEventById(request.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEventRequest")
    @ResponsePayload
    public AddEventResponse addEvent(@RequestPayload AddEventRequest request) {
        return eventService.addEvent(request.getEventDto());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "generateEventsPDFRequest")
    @ResponsePayload
    public GenerateEventsPDFResponse generateEventsPDF(@RequestPayload GenerateEventsPDFRequest request) {
        return eventService.generateEventsListAsPDF();
    }
}