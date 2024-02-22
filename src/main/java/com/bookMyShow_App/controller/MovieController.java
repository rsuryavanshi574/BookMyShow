package com.bookMyShow_App.controller;

import com.bookMyShow_App.dto.MovieDto;
import com.bookMyShow_App.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto){
        MovieDto movieCreated = movieService.createMovie(movieDto);
        return new ResponseEntity<>(movieCreated, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovie(){
        List<MovieDto> allMovie = movieService.getAllMovie();
        return new ResponseEntity<>(allMovie, HttpStatus.OK);
    }

    @GetMapping("/search/{movieName}")
    public ResponseEntity<List<MovieDto>> getMoviesByMovieNameContains(@PathVariable String movieName){
        List<MovieDto> moviesByMovieNameContains = movieService.searchMovies(movieName);
        return new ResponseEntity<>(moviesByMovieNameContains, HttpStatus.OK);
    }
}
