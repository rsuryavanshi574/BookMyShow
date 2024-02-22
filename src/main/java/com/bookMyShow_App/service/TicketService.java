package com.bookMyShow_App.service;

import com.bookMyShow_App.dto.TicketDto;

import java.util.List;


public interface TicketService {

    TicketDto ticketBooking(TicketDto ticketDto);

    List<TicketDto> getAllTicket();

    List<TicketDto> getTicketByEmail(String email);



}
