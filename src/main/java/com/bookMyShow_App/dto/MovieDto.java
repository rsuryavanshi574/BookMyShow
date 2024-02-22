package com.bookMyShow_App.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class MovieDto {

    private Integer id;

    private String moviePoster;

    @NotEmpty(message = "movie name should not be empty")
    private String movieName;

    @NotNull(message = "release date should not be null")
    private LocalDate releaseDate;

    @NotEmpty(message = "category should not be empty")
    private String category;

    @NotEmpty(message = "genre should not be empty")
    private String genre;

    @NotNull(message = "rating should not be null")
    private Double rating;

    @NotEmpty(message = "country should not be empty")
    private String country;

}
