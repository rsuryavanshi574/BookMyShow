package com.bookMyShow_App.service.impl;

import com.bookMyShow_App.constants.AppConstants;
import com.bookMyShow_App.dto.TicketDto;
import com.bookMyShow_App.entity.BookedSeat;
import com.bookMyShow_App.entity.Movie;
import com.bookMyShow_App.entity.Ticket;
import com.bookMyShow_App.entity.User;
import com.bookMyShow_App.exception.DataAlreadyExistException;
import com.bookMyShow_App.exception.DataNotFoundException;
import com.bookMyShow_App.exception.LimitExceedException;
import com.bookMyShow_App.repository.*;
import com.bookMyShow_App.service.TicketService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private BookedSeatRepository bookedSeatRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AtmCardRepository atmCardRepository;

    @Transactional
    @Override
    public TicketDto ticketBooking(TicketDto ticketDto) {
        Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
        String location = ticket.getLocation();
        String theater = ticket.getTheater();
        String time = ticket.getTime();
        List<BookedSeat> bookedSeatList = ticket.getBookedSeats();

        List<Ticket> byLocation = ticketRepository.findByLocation(location);
        List<Ticket> byTheater = ticketRepository.findByTheater(theater);
        List<Ticket> byTime = ticketRepository.findByTime(time);

        if (Stream.of(byLocation, byTheater, byTime)
                .anyMatch(list -> !list.isEmpty())
                &&
                Stream.of(byLocation, byTheater, byTime)
                        .filter(list -> !list.isEmpty())
                        .count() >= 3
        ) {

            String location1 = byLocation.get(0).getLocation();
            String theater1 = byTheater.get(0).getTheater();
            String time1 = byTime.get(0).getTime();

            if (location.equals(location1) && theater.equals(theater1) && time.equals(time1)) {
                bookedSeatList.stream()
                        .map(BookedSeat::getSeatNo)
                        .anyMatch(bookedSeat1 -> {
                            List<BookedSeat> byBookedSeat = bookedSeatRepository.findBySeatNo(bookedSeat1);

                            if (!byBookedSeat.isEmpty()) {
                                throw new DataAlreadyExistException("Seat", "Seat No", bookedSeat1);
                            }

                            return false;
                        });
                boolean anyMatch = false;

                if (!anyMatch) {
                    return bookedTicket(ticket);
                }
            } else {
                return bookedTicket(ticket);
            }
        }
        return bookedTicket(ticket);
    }

    public TicketDto bookedTicket(Ticket ticket) {
        ticket.getBookedSeats().forEach(bookedSeat -> bookedSeat.setTicket(ticket));

        ticket.getAtmCard().setUser(ticket.getUser());
        String food = ticket.getFood();
        String[] foodSplit = food.split(" = ");

        double foodPrice = Double.parseDouble(foodSplit[1]);

        long count = ticket.getBookedSeats().size();
        if(count>4){
            throw new LimitExceedException("You are exceeded limit of seat booking. please book 4 or less than 4 seats");
        }

        double discount = 1;
        String cardNo = ticket.getAtmCard().getCardNo();
        if(!cardNo.equals(atmCardRepository.findByCardNo(cardNo))){
            discount = AppConstants.DISCOUNT;
        }

        double totalPrice = foodPrice + (ticket.getPrice() * count);

        if(discount==AppConstants.DISCOUNT) {
            double totalPriceWithDiscount = totalPrice - (totalPrice * discount);
            ticket.setTotalPrice(totalPriceWithDiscount);
        }else {
            ticket.setPrice(totalPrice);
        }
        Ticket ticketSaved = ticketRepository.save(ticket);

        Integer userId = ticketSaved.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User", "id", String.valueOf(userId)));
        ticketSaved.setUser(user);

        Integer movieId = ticketSaved.getMovie().getId();
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new DataNotFoundException("Movie", "Id", String.valueOf(movieId)));
        ticketSaved.setMovie(movie);

        return modelMapper.map(ticketSaved, TicketDto.class);
    }

    @Override
    public List<TicketDto> getAllTicket() {
        List<Ticket> ticketList = ticketRepository.findAll();
        return ticketList.stream().map(ticket -> modelMapper.map(ticket, TicketDto.class)).toList();
    }

    @Override
    public List<TicketDto> getTicketByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundException("ticket", "email id", email));

        List<Ticket> tickets = user.getTickets();

        return tickets.stream().map(ticket -> modelMapper.map(ticket, TicketDto.class)).toList();
    }
}
