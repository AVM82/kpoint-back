package ua.in.kp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.in.kp.dto.user.UserCreateRequestDto;
import ua.in.kp.dto.user.UserResponseDto;
import ua.in.kp.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateRequestDto dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.OK);
    }
}
