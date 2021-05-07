package com.example.producingwebservice.repository;

import com.bialystok.event.ws.Event;
import com.bialystok.event.ws.Type;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class EventRepository {
    private final Map<Integer, Event> events = new HashMap<>();

    @PostConstruct
    public void initData() {
        Event event1 = new Event();
        event1.setId(0);
        event1.setName("Event 0");
        event1.setType(Type.SPORT);
        event1.setDate(XMLGregorianCalendarImpl.createDateTime(2021, 5, 10, 12, 0, 0));
        event1.setDescription("Long description of Event 1");
        event1.setYear(2021);
        event1.setMonth(5);
        event1.setWeek(15);

        events.put(event1.getId(), event1);

        Event event2 = new Event();
        event2.setId(1);
        event2.setName("Event 1");
        event2.setType(Type.CULTURAL);
        event2.setDate(XMLGregorianCalendarImpl.createDateTime(2021, 1, 20, 22, 30, 0));
        event2.setDescription("Long description of Event 2");
        event2.setYear(2021);
        event2.setMonth(1);
        event2.setWeek(4);

        events.put(event2.getId(), event2);
    }

    public List<Event> findAll() {
        return events.values()
                .stream()
                .sorted((e1, e2) -> e1.getDate().compare(e2.getDate()))
                .collect(Collectors.toList());
    }

    public List<Event> findByDate(XMLGregorianCalendar date) {
        Assert.notNull(date, "The date must not be null");
        return events.values()
                .stream()
                .filter(event -> event.getDate().getYear() == date.getYear() &&
                                 event.getDate().getMonth() == date.getMonth() &&
                                 event.getDate().getDay() == date.getDay())
                .collect(Collectors.toList());
    }

    public List<Event> findByWeek(Integer week) {
        Assert.notNull(week, "The week must not be null");
        return events.values()
                .stream()
                .filter(event -> event.getWeek() == week)
                .collect(Collectors.toList());
    }

    public Event findById(Integer id) {
        Assert.notNull(id, "The event's id must not be null");
        return events.get(id);
    }

    public Event add(Event event) {
        int id = events.size();
        event.setId(id);
        events.put(id, event);
        return event;
    }

    public Event update(Event eventReq) {
        Event event = events.get(eventReq.getId());
        if (event == null) {
            return null;
        }
        event.setName(eventReq.getName());
        event.setType(eventReq.getType());
        event.setDate(eventReq.getDate());
        event.setDescription(eventReq.getDescription());
        event.setYear(eventReq.getYear());
        event.setMonth(eventReq.getMonth());
        event.setWeek(eventReq.getWeek());
        return event;
    }

    public Event delete(Integer eventId) {
        Event event = events.get(eventId);
        if (event == null) {
            return null;
        }
        return events.remove(eventId);
    }
}
