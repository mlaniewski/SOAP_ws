package com.example.producingwebservice.service;

import com.bialystok.event.ws.EventDetailsResponse;
import com.bialystok.event.ws.EventDto;
import com.bialystok.event.ws.EventsListPDFResponse;
import com.bialystok.event.ws.EventsListResponse;

import javax.xml.datatype.XMLGregorianCalendar;

public interface EventService {
    EventsListResponse getAllEvents();
    EventsListResponse getEventsByDate(XMLGregorianCalendar date);
    EventsListResponse getEventsByWeek(Integer week);
    EventDetailsResponse getEventById(Integer id);
    EventDetailsResponse addEvent(EventDto eventDto);
    EventsListPDFResponse generateEventsListAsPDF();
}
