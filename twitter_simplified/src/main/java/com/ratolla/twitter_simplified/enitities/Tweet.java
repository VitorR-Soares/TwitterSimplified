package com.ratolla.twitter_simplified.enitities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Lombok;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table (name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column (name = "tweet_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private Instant creationTimeStamp;
}
