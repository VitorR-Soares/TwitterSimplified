package com.ratolla.twitter_simplified.controllers;

import com.ratolla.twitter_simplified.controllers.dto.CreateTweetDTO;
import com.ratolla.twitter_simplified.controllers.dto.FeedDTO;
import com.ratolla.twitter_simplified.controllers.dto.FeedItemDTO;
import com.ratolla.twitter_simplified.enitities.Role;
import com.ratolla.twitter_simplified.enitities.Tweet;
import com.ratolla.twitter_simplified.enitities.User;
import com.ratolla.twitter_simplified.repositories.TweetRepository;
import com.ratolla.twitter_simplified.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class TweetController {

    private final TweetRepository tweetRepository;

    private  final UserRepository userRepository;

    public TweetController(TweetRepository tweetRepository,
                           UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/createTweet")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDTO dto, JwtAuthenticationToken token){

        System.out.println(token.getName());

        var user = userRepository.findById(token.getName());

        String content = dto.content();

        Tweet newTweet = new Tweet();

        newTweet.setUser(user.get());
        newTweet.setContent(content);

        tweetRepository.save(newTweet);


        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/deleteTweet/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long id,
                                            JwtAuthenticationToken token){

        var user = userRepository.findById(token.getName());

        System.out.println(user.get().getUsername());

        var tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        System.out.println(tweet.getContent());

        var isAdm = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if(user.get().getId().equals(token.getName()) || isAdm){
            tweetRepository.delete(tweet);
        } else {
            ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/feed")
    public ResponseEntity<FeedDTO> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        var tweets = tweetRepository.findAll(PageRequest.of(
                page, pageSize, Sort.Direction.DESC, "creationTimeStamp"))
                .map(tweet -> new FeedItemDTO(tweet.getId(), tweet.getContent(), tweet.getUser().getUsername()));

        return ResponseEntity.ok(new FeedDTO(tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements()));
    }
}
