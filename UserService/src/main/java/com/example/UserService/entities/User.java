package com.example.UserService.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User
{
    @Id
    @Column(name = "ID")
    String userId;
    @Column(name = "NAME")
    String name;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "ABOUT")
    String about;

    @Transient
    List<Rating> ratings;
}