package com.bookMyShow_App.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String moviePoster;
    private String movieName;
    private LocalDate releaseDate;
    private String category;
    private String genre;
    private Double rating;
    private String country;

    @OneToMany(mappedBy = "movie")
    private List<Ticket> tickets;


}
