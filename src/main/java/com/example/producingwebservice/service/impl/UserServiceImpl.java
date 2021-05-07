package com.example.producingwebservice.service.impl;

import com.bialystok.event.ws.*;
import com.example.producingwebservice.repository.UserRepository;
import com.example.producingwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        User user = userRepository.get(request.getUsername(), request.getPassword());
        response.setUser(user);
        return response;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        User saved = userRepository.save(request.getUsername(), request.getPassword());
        if (saved == null) {
            response.setStatus(OpStatusCode.FAULT);
        } else {
            response.setStatus(OpStatusCode.OK);
        }
        return response;
    }
}
