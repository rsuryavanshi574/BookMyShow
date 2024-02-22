package com.bookMyShow_App.service;

import com.bookMyShow_App.dto.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto createMovie(MovieDto movieDto);

   /* MovieDto updateMovie(Integer movieId, MovieDto movieDto);

    String deleteMovie(Integer movieId);*/

    List<MovieDto> getAllMovie();

    //List<MovieDto> getMoviesByMovieNameContains(String movieName);

    List<MovieDto> searchMovies(String movieName);

}
