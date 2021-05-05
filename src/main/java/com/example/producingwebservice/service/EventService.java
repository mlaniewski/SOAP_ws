package com.example.producingwebservice.service;

import com.bialystok.event.ws.*;

import javax.xml.datatype.XMLGregorianCalendar;

public interface EventService {
    GetAllEventsResponse getAllEvents();
    GetEventsByDateResponse getEventsByDate(XMLGregorianCalendar date);
    GetEventsByWeekResponse getEventsByWeek(Integer week);
    GetEventDetailsByIdResponse getEventById(Integer id);
    AddEventResponse addEvent(EventDto eventDto);
    GenerateEventsPDFResponse generateEventsListAsPDF();
}
