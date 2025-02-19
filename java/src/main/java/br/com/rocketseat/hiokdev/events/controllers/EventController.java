package br.com.rocketseat.hiokdev.events.controllers;

import br.com.rocketseat.hiokdev.events.models.Event;
import br.com.rocketseat.hiokdev.events.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PostMapping("/events")
    public Event addNewEvent(@RequestBody Event newEvent){
        return service.addNewEvent(newEvent);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents(){
        return service.getAllEvents();
    }

    @GetMapping("/events/{prettyName}")
    public Event getEventByPrettyName(@PathVariable String prettyName){
        return service.getByPrettyName(prettyName);
    }

}
