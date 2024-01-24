package ua.in.kp.service;

import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.in.kp.dto.user.UserCreateRequestDto;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.entity.UserEntity;
import ua.in.kp.mapper.UserMapper;
import ua.in.kp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private static final String HEADER_NAME = "Authorization";
    private static final String TOKEN = "Basic ";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserResponseDto register(UserCreateRequestDto requestDto) {
        if (userRepository.existsAllByEmail(requestDto.email())) {
            throw new EntityExistsException(
                    "User with email " + requestDto.email() + " already exist");
        }

        UserCreateRequestDto dtoToSave = userMapper.toDtoWithEncode(requestDto,
                passwordEncoder.encode(requestDto.password()));

        UserEntity userEntity = userRepository.save(userMapper.toEntity(dtoToSave));
        return userMapper.toDto(userEntity);
    }

    public UserResponseDto login(HttpServletRequest request) {
        UserResponseDto responseDto = null;
        String header = request.getHeader(HEADER_NAME);
        if (header != null && header.startsWith(TOKEN)) {
            String[] credentials = parseToken(header);

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Authentication authenticate = authenticationManager.authenticate(authentication);
            responseDto = userMapper.toDto((UserEntity) authenticate.getPrincipal());
        }
        return responseDto;
    }

    private static String[] parseToken(String header) {
        String base64Credentials = header.substring(TOKEN.length()).trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
        return decodedCredentials.split(":", 2);
    }
}
