package com.example.producingwebservice.endpoint;

import com.bialystok.event.ws.GetAllEventsRequest;
import com.example.producingwebservice.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EventEndpoint {
    private static final String NAMESPACE_URI = "http://ws.event.bialystok.com";

    @Autowired
    private EventServiceImpl eventService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllEventsRequest")
    @ResponsePayload
    public GetAllEventsRequest getAllEvents() {
        return eventService.getAllEvents();
    }
}