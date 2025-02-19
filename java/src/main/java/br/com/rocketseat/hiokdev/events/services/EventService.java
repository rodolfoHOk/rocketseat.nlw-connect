package br.com.rocketseat.hiokdev.events.services;

import br.com.rocketseat.hiokdev.events.exceptions.NotFoundException;
import br.com.rocketseat.hiokdev.events.models.Event;
import br.com.rocketseat.hiokdev.events.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addNewEvent(Event event){
        event.setPrettyName(event.getTitle().toLowerCase().replaceAll(" ", "-"));
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public Event getByPrettyName(String prettyName){
        return eventRepository.findByPrettyName(prettyName)
                .orElseThrow(() -> new NotFoundException("Event not found with pretty name: " + prettyName));
    }

}
