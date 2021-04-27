package com.example.producingwebservice.service;

import com.bialystok.event.ws.Event;

import java.io.File;
import java.util.List;

public interface PDFCreator {
    File create(List<Event> eventList);
}
