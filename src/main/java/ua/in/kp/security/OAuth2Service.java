package ua.in.kp.security;

import javax.naming.AuthenticationException;
import ua.in.kp.dto.user.UserLoginResponseDto;

public interface OAuth2Service {

    UserLoginResponseDto handleCode(String code) throws AuthenticationException;
}
