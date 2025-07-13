package com.example.UserService.controller;

import com.example.UserService.entities.User;
import com.example.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    Logger logger= LoggerFactory.getLogger(UserService.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{id}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallBack")
    @RateLimiter(name = "rateLimiterExample", fallbackMethod = "fallbackMethod")
    public ResponseEntity<User> getUser(@PathVariable String id)
    {
        User user1=userService.getUser(id);
        return ResponseEntity.ok(user1);
    }

    public ResponseEntity<User> ratingHotelFallBack(String id,Throwable t) {
       User user1= User.builder().email("dummy@gmil.com").about("Service is down..").userId("11").name("Srushti").build();
        return ResponseEntity.ok(user1);
    }

    int retry=1;
    @GetMapping
    @Retry(name = "ratingHotelRetry",fallbackMethod = "ratingHotelFallBackRetry")
    public ResponseEntity<List<User>> getAll()
    {
        logger.info("Retried for {}",retry," many times");
        retry++;
        List<User> userList=userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
    public ResponseEntity<User> ratingHotelFallBackRetry(Throwable t) {
        User user1= User.builder().email("dummy@gmil.com").about("Service is down..").userId("11").name("Srushti").build();
        return ResponseEntity.ok(user1);
    }
}
