package br.com.rocketseat.hiokdev.events.controllers;

import br.com.rocketseat.hiokdev.events.dto.SubscriptionResponse;
import br.com.rocketseat.hiokdev.events.models.User;
import br.com.rocketseat.hiokdev.events.services.SubscriptionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
