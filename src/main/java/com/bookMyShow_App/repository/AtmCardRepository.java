package com.bookMyShow_App.repository;

import com.bookMyShow_App.entity.AtmCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtmCardRepository extends JpaRepository<AtmCard, Integer> {

    String findByCardNo(String cardNo);
}
