package br.com.rocketseat.hiokdev.events.repositories;

import br.com.rocketseat.hiokdev.events.models.Event;
import br.com.rocketseat.hiokdev.events.models.Subscription;
import br.com.rocketseat.hiokdev.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Optional<Subscription> findByEventAndSubscriber(Event evt, User user);

}
