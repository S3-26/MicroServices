package com.example.HotelService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel
{
    @Id
    @Column(name = "ID")
    String hotelId;
    @Column(name = "NAME")
    String name;
    @Column(name = "LOCATION")
    String location;
    @Column(name = "ABOUT")
    String about;

    @Transient
    List<Rating> ratings;
}