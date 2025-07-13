package com.example.RatingService.controller;

import com.example.RatingService.entities.Rating;
import com.example.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
    {
        Rating rating1=ratingService.saveRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId)
    {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId)
    {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAll()
    {
        List<Rating> ratingList=ratingService.getAllRatings();
        return ResponseEntity.ok(ratingList);
    }
}
