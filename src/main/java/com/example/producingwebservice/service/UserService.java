package com.example.producingwebservice.service;

import com.bialystok.event.ws.LoginRequest;
import com.bialystok.event.ws.LoginResponse;
import com.bialystok.event.ws.RegisterRequest;
import com.bialystok.event.ws.RegisterResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);
    RegisterResponse register(RegisterRequest request);
}
