package com.bookMyShow_App.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;

    private String location;
    private String theater;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookedSeat> bookedSeats;

    private String time;
    private String food;
    private Double price;
    private Double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AtmCard atmCard;

}
