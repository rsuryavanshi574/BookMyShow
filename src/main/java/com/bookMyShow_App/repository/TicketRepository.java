package com.bookMyShow_App.repository;

import com.bookMyShow_App.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    String findByMovie(String movieName);

    List<Ticket> findByTheater(String theaterName);

    List<Ticket> findByLocation(String location);

    List<Ticket> findByTime(String time);



}
