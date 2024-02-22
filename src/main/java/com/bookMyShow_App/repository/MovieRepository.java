package com.bookMyShow_App.repository;

import com.bookMyShow_App.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    //List<Movie> findByMovieNameContainsAllIgnoreCase(String movieName);
    List<Movie> findByMovieNameContainingAllIgnoreCase(String movieName);
}
