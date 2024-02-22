package com.bookMyShow_App.service.impl;

import com.bookMyShow_App.constants.AppConstants;
import com.bookMyShow_App.dto.UserDto;
import com.bookMyShow_App.entity.User;
import com.bookMyShow_App.entity.UserRole;
import com.bookMyShow_App.repository.UserRepository;
import com.bookMyShow_App.repository.UserRoleRepository;
import com.bookMyShow_App.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserRole userRole = userRoleRepository.findById(AppConstants.NORMAL_USER).get();

        user.getUserRoles().add(userRole); //TODO incomplete

        User userSaved = userRepository.save(user);
        return modelMapper.map(userSaved, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
    }
}
