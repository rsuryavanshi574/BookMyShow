package com.bookMyShow_App.entity;

import lombok.Getter;
import lombok.Setter;
;import javax.persistence.*;


@Getter
@Setter
@Entity
public class AtmCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cardNo;
    private String cardHolder;
    private String expDate;
    private String cvv;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;


}
