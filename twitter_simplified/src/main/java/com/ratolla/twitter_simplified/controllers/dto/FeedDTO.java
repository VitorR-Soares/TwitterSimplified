package com.ratolla.twitter_simplified.controllers.dto;

import java.util.List;

public record FeedDTO(List<FeedItemDTO> items,
                      int page,
                      int pageSize,
                      int totalPages,
                      long totalElements) {
}
