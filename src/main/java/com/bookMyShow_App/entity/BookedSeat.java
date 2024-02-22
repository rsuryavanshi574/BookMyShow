package com.bookMyShow_App.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BookedSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String seatNo;


    @ManyToOne
    private Ticket ticket;

}
