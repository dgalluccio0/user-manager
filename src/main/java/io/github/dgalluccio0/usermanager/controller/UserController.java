package io.github.dgalluccio0.usermanager.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dgalluccio0.usermanager.dto.UpdatePasswordDTO;
import io.github.dgalluccio0.usermanager.dto.UserDTO;
import io.github.dgalluccio0.usermanager.model.CustomUserDetails;
import io.github.dgalluccio0.usermanager.model.User;
import io.github.dgalluccio0.usermanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users/me", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(path = "/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updatePassword(
            @Valid @RequestBody UpdatePasswordDTO dto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        User updatedPasswordUser = service.updatePassword(userDetails.getId(), dto);
        return ResponseEntity.ok(service.toUserDTO(updatedPasswordUser));
    }
}