package com.example.HotelService.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating
{
    String ratingId;
    String userId;
    String hotelId;
    int rating;
    String feedback;
}