package br.com.rocketseat.hiokdev.events.dto;

public record SubscriptionRankingByUser(
        SubscriptionRankingItem item,
        Integer rankingPosition
) {
}
