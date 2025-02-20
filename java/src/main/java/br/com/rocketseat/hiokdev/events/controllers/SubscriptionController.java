package br.com.rocketseat.hiokdev.events.controllers;

import br.com.rocketseat.hiokdev.events.dto.SubscriptionRankingByUser;
import br.com.rocketseat.hiokdev.events.dto.SubscriptionRankingItem;
import br.com.rocketseat.hiokdev.events.dto.SubscriptionResponse;
import br.com.rocketseat.hiokdev.events.models.User;
import br.com.rocketseat.hiokdev.events.services.SubscriptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping({"/subscription/{prettyName}", "/subscription/{prettyName}/{userId}"})
    public SubscriptionResponse createSubscription(
            @PathVariable String prettyName,
            @RequestBody User subscriber,
            @PathVariable(required = false) Integer userId
    ){
        return subscriptionService.createNewSubscription(prettyName, subscriber, userId);
    }

    @GetMapping("/subscription/{prettyName}/ranking")
    public List<SubscriptionRankingItem> generateRankingByEvent(@PathVariable String prettyName){
        return subscriptionService.getCompleteRanking(prettyName).subList(0, 3);
    }

    @GetMapping("/subscription/{prettyName}/ranking/{userId}")
    public SubscriptionRankingByUser generateRankingByEventAndUser(@PathVariable String prettyName, @PathVariable Integer userId){
        return subscriptionService.getRankingByUser(prettyName, userId);
    }

}
