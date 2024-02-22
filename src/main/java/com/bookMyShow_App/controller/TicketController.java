package com.bookMyShow_App.controller;

import com.bookMyShow_App.dto.TicketDto;
import com.bookMyShow_App.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@CrossOrigin
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketDto> addBooking(@Valid @RequestBody TicketDto ticketDto){
        TicketDto addedBooking = ticketService.ticketBooking(ticketDto);
        return new ResponseEntity<>(addedBooking, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTicket(){
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getAllTicket());
    }

    @GetMapping("{email}")
    public ResponseEntity<List<TicketDto>> getTicketByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.getTicketByEmail(email));
    }
}
