package com.bookMyShow_App.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AtmCardDto {

    private Integer id;

    @NotEmpty(message = "card no should not be empty")
    @Pattern(regexp = "^[0-9]{16}$")
    private String cardNo;

    @NotEmpty(message = "card holder name should not be empty")
    private String cardHolder;

    @NotEmpty(message = "exp date should not be empty")
    private String expDate;

    @NotEmpty(message = "cvv should not be empty")
    @Pattern(regexp = "^[0-9]{3}$")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String cvv;


}
