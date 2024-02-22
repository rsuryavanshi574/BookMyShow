package com.bookMyShow_App.service;

import com.bookMyShow_App.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto userDto);

    List<UserDto> getAllUser();




}
