package br.com.rocketseat.hiokdev.events.repositories;

import br.com.rocketseat.hiokdev.events.dto.SubscriptionRankingItem;
import br.com.rocketseat.hiokdev.events.models.Event;
import br.com.rocketseat.hiokdev.events.models.Subscription;
import br.com.rocketseat.hiokdev.events.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Optional<Subscription> findByEventAndSubscriber(Event evt, User user);

    @Query(value = """
                select count(subscription_number) as quantity, indication_user_id, user_name
                from tbl_subscription inner join tbl_user
                on tbl_subscription.indication_user_id = tbl_user.user_id
                where indication_user_id is not null and event_id = :eventId
                group by indication_user_id
                order by quantity desc
                """,
            nativeQuery = true
    )
    List<SubscriptionRankingItem> generateRanking(@Param("eventId") Integer eventId);

}
