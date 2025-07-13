package com.example.HotelService.controller;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        Hotel hotel1=hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id)
    {
        Hotel hotel1=hotelService.getHotel(id);
        return ResponseEntity.ok(hotel1);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        List<Hotel> hotelList=hotelService.getAllHotels();
        return ResponseEntity.ok(hotelList);
    }
}
