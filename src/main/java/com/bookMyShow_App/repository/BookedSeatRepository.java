package com.bookMyShow_App.repository;

import com.bookMyShow_App.entity.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedSeatRepository extends JpaRepository<BookedSeat, Integer> {

    List<BookedSeat> findBySeatNo(String bookedSeat);


}
