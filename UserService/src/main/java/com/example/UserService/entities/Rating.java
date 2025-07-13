package com.example.UserService.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

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
    Hotel hotel;
}