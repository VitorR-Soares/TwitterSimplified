package com.ratolla.twitter_simplified.controllers.dto;

public record FeedItemDTO(Long tweetId,
                          String tweetContent,
                          String username) {
}
