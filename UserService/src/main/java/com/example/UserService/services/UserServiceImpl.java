package com.example.UserService.services;
import com.example.UserService.externalServices.HotelService;
import com.netflix.discovery.converters.Auto;
import org.springframework.core.ParameterizedTypeReference;
import com.example.UserService.entities.Hotel;
import com.example.UserService.entities.Rating;
import com.example.UserService.entities.User;
import com.example.UserService.exceptions.ResourceNoFoundException;
import com.example.UserService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HotelService hotelService;

    @Override
    public User saveUser(User user) {
        String random= UUID.randomUUID().toString();
        user.setUserId(random);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users= userRepository.findAll();
        users.forEach(user-> {
                    ArrayList<Rating> ratingList = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), ArrayList.class);
                    user.setRatings(ratingList);
                });
        return users;
    }

    @Override
    public User getUser(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNoFoundException("User with given id is missing"));

        // Properly typed response for List<Rating>
        ResponseEntity<List<Rating>> ratingResponse = restTemplate.exchange(
                "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Rating>>() {}
        );

        List<Rating> ratingList = ratingResponse.getBody();

        // For each rating, fetch hotel and set it
        List<Rating> enrichedRatings = ratingList.stream().map(rating -> {
//            ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(
//                    "http://HOTEL-SERVICE/hotels/" + rating.getHotelId(),
//                    Hotel.class
//            );
//            rating.setHotel(hotelResponse.getBody());
//            return rating;

            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;


        }).collect(Collectors.toList());
        user.setRatings(enrichedRatings);

        return user;
    }
}
