package com.example.producingwebservice.repository;

import com.bialystok.event.ws.Event;
import com.bialystok.event.ws.Type;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class EventRepository {
    private static final Map<Integer, Event> EVENTS = new HashMap<>();

    @PostConstruct
    public void initData() {
        Event event1 = new Event();
        event1.setId(1);
        event1.setName("Event 1");
        event1.setType(Type.SPORT);
        event1.setDate(XMLGregorianCalendarImpl.createDateTime(2021, 5, 10, 12, 0, 0));
        event1.setDescription("Long description of Event 1");
        event1.setYear(2021);
        event1.setMonth(5);
        event1.setWeek(15);

        EVENTS.put(event1.getId(), event1);

        Event event2 = new Event();
        event2.setId(2);
        event2.setName("Event 2");
        event2.setType(Type.CULTURAL);
        event2.setDate(XMLGregorianCalendarImpl.createDateTime(2021, 1, 20, 22, 30, 0));
        event2.setDescription("Long description of Event 2");
        event2.setYear(2021);
        event2.setMonth(1);
        event2.setWeek(4);

        EVENTS.put(event2.getId(), event2);
    }

    public Event findEvent(Integer id) {
        Assert.notNull(id, "The event's id must not be null");
        return EVENTS.get(id);
    }

    public List<Event> findAll() {
        List<Event> events = new LinkedList<>();
        EVENTS.forEach((id, event) -> {
            events.add(event);
        });
        return events;
    }
}
