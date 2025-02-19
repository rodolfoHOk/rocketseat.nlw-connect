package br.com.rocketseat.hiokdev.events.repositories;

import br.com.rocketseat.hiokdev.events.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Optional<Event> findByPrettyName(String prettyName);

}
