package br.com.rocketseat.hiokdev.events.dto;

public record SubscriptionRankingItem(
        Long subscribers,
        Integer userId,
        String name
) {
}
