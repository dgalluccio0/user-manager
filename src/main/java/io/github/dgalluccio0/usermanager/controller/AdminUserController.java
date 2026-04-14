package io.github.dgalluccio0.usermanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dgalluccio0.usermanager.dto.CreateUserDTO;
import io.github.dgalluccio0.usermanager.dto.PatchUserDTO;
import io.github.dgalluccio0.usermanager.dto.ResetPasswordDTO;
import io.github.dgalluccio0.usermanager.dto.UpdatePasswordDTO;
import io.github.dgalluccio0.usermanager.dto.UpdateRoleDTO;
import io.github.dgalluccio0.usermanager.dto.UpdateUserDTO;
import io.github.dgalluccio0.usermanager.dto.UserDTO;
import io.github.dgalluccio0.usermanager.model.CustomUserDetails;
import io.github.dgalluccio0.usermanager.model.User;
import io.github.dgalluccio0.usermanager.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminUserController {

    private final UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody CreateUserDTO dto) {
        User user = service.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.toUserDTO(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(service.getAllDTO());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getByIdDTO(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.getByEmailDTO(email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.getByUsernameDTO(username));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUserDTO dto) {
        User updatedUser = service.updateUser(id, dto);
        return ResponseEntity.ok(service.toUserDTO(updatedUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> patchUser(
            @PathVariable Integer id,
            @Valid @RequestBody PatchUserDTO dto) {
        User patchedUser = service.patchUser(id, dto);
        return ResponseEntity.ok(service.toUserDTO(patchedUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}/role", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateRole(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateRoleDTO dto) {
        User updatedRoleUser = service.updateRoleUser(id, dto);
        return ResponseEntity.ok(service.toUserDTO(updatedRoleUser));
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping(path = "/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updatePassword(
                @Valid @RequestBody UpdatePasswordDTO dto,
                @AuthenticationPrincipal CustomUserDetails userDetails) {
        User updatedPasswordUser = service.updatePassword(userDetails.getId(), dto);
        return ResponseEntity.ok(service.toUserDTO(updatedPasswordUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "{id}/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> resetPassword(
            @PathVariable Integer id,
            @Valid @RequestBody ResetPasswordDTO dto) {
        User resettedPasswordUser = service.resetPassword(id, dto);
        return ResponseEntity.ok(service.toUserDTO(resettedPasswordUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}