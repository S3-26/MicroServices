package com.example.RatingService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user_ratings")
public class Rating
{
    @Id
    String ratingId;
    String userId;
    String hotelId;
    int rating;
    String feedback;
}