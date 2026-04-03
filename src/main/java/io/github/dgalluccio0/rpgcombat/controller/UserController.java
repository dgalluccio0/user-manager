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
        UserDTO response = service.toUserDTO(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUserDTO dto) {
        try {
            User updatedUser = service.updateUser(id, dto);
            if (updatedUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            UserDTO response = service.toUserDTO(updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> patchUser(
            @PathVariable Integer id,
            @RequestBody PatchUserDTO dto) {
        try {
            User patchedUser = service.patchUser(id, dto);
            if (patchedUser == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            UserDTO response = service.toUserDTO(patchedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        try {
            List<UserDTO> users = service.getAllDTO();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        try {
            UserDTO user = service.getByIdDTO(id);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
        try {
            UserDTO user = service.getByEmailDTO(email);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getByUsername(@PathVariable String username) {
        try {
            UserDTO user = service.getByUsernameDTO(username);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        try {
            Boolean deleted = service.deleteById(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}