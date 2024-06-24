package com.ne.starter.serviceImpl;

import com.ne.starter.dtos.requests.SignInDto;
import com.ne.starter.services.IAuthenticationService;
import org.springframework.stereotype.Service;

@Service

public class AuthenticationServiceImpl implements IAuthenticationService {
    @Override
    public <T> T login(SignInDto dto) {
        return null;
    }
}
