package com.ratolla.twitter_simplified.repositories;

import com.ratolla.twitter_simplified.enitities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
