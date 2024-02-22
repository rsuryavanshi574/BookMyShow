package com.bookMyShow_App.service.impl;

import com.bookMyShow_App.dto.MovieDto;
import com.bookMyShow_App.entity.Movie;
import com.bookMyShow_App.exception.DataAlreadyExistException;
import com.bookMyShow_App.exception.DataNotFoundException;
import com.bookMyShow_App.repository.MovieRepository;
import com.bookMyShow_App.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie movieSaved = movieRepository.save(movie);
        return modelMapper.map(movieSaved, MovieDto.class);
    }

    @Override
    public List<MovieDto> getAllMovie() {
        List<Movie> movieList = movieRepository.findAll();
        return movieList.stream().map(movie -> modelMapper.map(movie, MovieDto.class)).toList();
    }

    @Override
    public List<MovieDto> searchMovies(String movieName) {
        List<Movie> movies = movieRepository.findByMovieNameContainingAllIgnoreCase(movieName);
        if(movies.isEmpty()){
            throw new DataNotFoundException("Movie", "movie name", movieName);
        }
        return movies.stream().map(movie -> modelMapper.map(movie, MovieDto.class)).toList();
    }



}
