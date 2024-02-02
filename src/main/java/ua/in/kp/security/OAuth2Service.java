package ua.in.kp.security;

import ua.in.kp.dto.user.UserLoginResponseDto;

import javax.naming.AuthenticationException;

public interface OAuth2Service {

    UserLoginResponseDto handleCode(String code) throws AuthenticationException;
}
