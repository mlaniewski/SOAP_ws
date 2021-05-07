package com.example.producingwebservice.endpoint;

import com.bialystok.event.ws.LoginRequest;
import com.bialystok.event.ws.LoginResponse;
import com.bialystok.event.ws.RegisterRequest;
import com.bialystok.event.ws.RegisterResponse;
import com.example.producingwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://ws.event.bialystok.com";

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        return userService.login(request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerRequest")
    @ResponsePayload
    public RegisterResponse register(@RequestPayload RegisterRequest request) {
        return userService.register(request);
    }
}