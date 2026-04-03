package io.github.dgalluccio0.rpgcombat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.dgalluccio0.rpgcombat.dto.CreateUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.PatchUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.UpdateUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.UserDTO;
import io.github.dgalluccio0.rpgcombat.model.User;
import io.github.dgalluccio0.rpgcombat.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody CreateUserDTO dto) {
        User user = service.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.toUserDTO(user));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUserDTO dto) {
        User updatedUser = service.updateUser(id, dto);
        return ResponseEntity.ok(service.toUserDTO(updatedUser));
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> patchUser(
            @PathVariable Integer id,
            @RequestBody PatchUserDTO dto) {
        User patchedUser = service.patchUser(id, dto);
        return ResponseEntity.ok(service.toUserDTO(patchedUser));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(service.getAllDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getByIdDTO(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.getByEmailDTO(email));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.getByUsernameDTO(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}