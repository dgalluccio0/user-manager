package io.github.dgalluccio0.usermanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dgalluccio0.usermanager.dto.CreateUserDTO;
import io.github.dgalluccio0.usermanager.dto.LoginDTO;
import io.github.dgalluccio0.usermanager.dto.UserDTO;
import io.github.dgalluccio0.usermanager.model.User;
import io.github.dgalluccio0.usermanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final UserService service;
    
    @PatchMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> register(
            @Valid @RequestBody CreateUserDTO dto) {
        User user = service.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.toUserDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(
        @Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(service.getByEmail(dto.getEmail()));
    }
}