package br.com.rocketseat.hiokdev.events.services;

import br.com.rocketseat.hiokdev.events.dto.SubscriptionRankingByUser;
import br.com.rocketseat.hiokdev.events.dto.SubscriptionRankingItem;
import br.com.rocketseat.hiokdev.events.dto.SubscriptionResponse;
import br.com.rocketseat.hiokdev.events.exceptions.ConflictException;
import br.com.rocketseat.hiokdev.events.exceptions.NotFoundException;
import br.com.rocketseat.hiokdev.events.models.Event;
import br.com.rocketseat.hiokdev.events.models.Subscription;
import br.com.rocketseat.hiokdev.events.models.User;
import br.com.rocketseat.hiokdev.events.repositories.EventRepository;
import br.com.rocketseat.hiokdev.events.repositories.SubscriptionRepository;
import br.com.rocketseat.hiokdev.events.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class SubscriptionService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(EventRepository eventRepository, UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {
        Event event = eventRepository.findByPrettyName(eventName)
                .orElseThrow(() -> new NotFoundException("Event not found with pretty name: " + eventName));

        User savedUser = userRepository.findByEmail(user.getEmail()).orElseGet(() -> userRepository.save(user));

        Optional<Subscription> existsSubscription = subscriptionRepository.findByEventAndSubscriber(event, savedUser);
        if (existsSubscription.isPresent()) {
            throw new ConflictException(
                    "Subscription already exists for user: " + user.getName() + " in event: " + event.getTitle());
        }

        Subscription subscription = new Subscription();
        subscription.setEvent(event);
        subscription.setSubscriber(savedUser);

        if (userId != null) {
            User indicator = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("Indicated user not exists by id: " + userId));
            subscription.setIndication(indicator);
        }

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return new SubscriptionResponse(
                savedSubscription.getSubscriptionNumber(),
                "http://codecraft.com/subscription/" +
                        savedSubscription.getEvent().getPrettyName() +
                        "/" +
                        savedSubscription.getSubscriber().getId());
    }

    public List<SubscriptionRankingItem> getCompleteRanking(String prettyName) {
        Event event = eventRepository.findByPrettyName(prettyName)
                .orElseThrow(() -> new NotFoundException("Event not found with pretty name: " + prettyName));
        return subscriptionRepository.generateRanking(event.getEventId());
    }

    public SubscriptionRankingByUser getRankingByUser(String prettyName, Integer userId) {
        List<SubscriptionRankingItem> ranking = getCompleteRanking(prettyName);

        SubscriptionRankingItem subscriptionRankingItem = ranking.stream()
                .filter(item -> item.userId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not exists indication for user: " + userId));

        int position = IntStream.range(0, ranking.size())
                .filter(pos -> ranking.get(pos).userId().equals(userId))
                .findFirst().orElseGet(() -> Integer.MAX_VALUE - 1);

        return new SubscriptionRankingByUser(subscriptionRankingItem, position + 1);
    }

}
