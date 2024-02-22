package com.bookMyShow_App.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TicketDto {

    private Integer id;

    @NotNull(message = "movie id should not be null")
    private MovieDto movie;

    @NotBlank(message = "location should not be blank")
    private String location;

    @NotBlank(message = "theater should not be blank")
    private String theater;

    @NotEmpty(message = "bookedSeats should not be empty")
    private List<BookedSeatDto> bookedSeats;

    @NotBlank(message = "time should not be blank")
    private String time;

    @NotBlank(message = "food should not be blank")
    private String food;

    @NotNull(message = "price should not be null")
    private Double price;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalPrice;

    @NotNull(message = "user id should not be null")
    private UserDto user;

    private AtmCardDto atmCard;
}
