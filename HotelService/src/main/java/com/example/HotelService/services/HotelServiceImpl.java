package com.example.HotelService.services;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.entities.Rating;
import com.example.HotelService.exceptions.ResourceNoFoundException;
import com.example.HotelService.repositories.HotelRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Hotel saveHotel(Hotel user) {
        String random= UUID.randomUUID().toString();
        user.setHotelId(random);
        return hotelRepository.save(user);
    }

    @Override
    public List<Hotel> getAllHotels() {
//        List<Hotel> hotels= hotelRepository.findAll();
//        hotels.forEach(hotel-> {
//            ArrayList<Rating> ratingList = restTemplate.getForObject("http://RATING-SERVICE/ratings/hotels/" + hotel.getHotelId(), ArrayList.class);
//            hotel.setRatings(ratingList);
//        });
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        Hotel hotel=hotelRepository.findById(id).orElseThrow(()->new ResourceNoFoundException("Hotel with given id is missing"));
//        ArrayList<Rating> ratingList= restTemplate.getForObject("http://RATING-SERVICE/ratings/hotels/"+hotel.getHotelId(), ArrayList.class);
//        hotel.setRatings(ratingList);
        return hotel;

    }
}
