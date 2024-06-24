package com.ne.starter.services;

import com.ne.starter.dtos.requests.SignInDto;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {

    public <T> T login(SignInDto dto);

}
