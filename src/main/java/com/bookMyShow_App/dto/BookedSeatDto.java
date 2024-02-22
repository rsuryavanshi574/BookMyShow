package com.bookMyShow_App.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookedSeatDto {

    private Integer id;

    @NotBlank(message = "please add at least one seat")
    private String seatNo;

    //private TicketDto ticketDto;
}
