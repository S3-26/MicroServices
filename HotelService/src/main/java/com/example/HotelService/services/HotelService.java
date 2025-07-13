package com.example.HotelService.services;

import com.example.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHotels();
    Hotel getHotel(String id);

}
