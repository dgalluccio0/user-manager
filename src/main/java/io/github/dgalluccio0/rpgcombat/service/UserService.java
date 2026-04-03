package io.github.dgalluccio0.rpgcombat.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.dgalluccio0.rpgcombat.dto.CreateUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.PatchUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.UpdateUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.UserDTO;
import io.github.dgalluccio0.rpgcombat.exceptions.ResourceNotFoundException;
import io.github.dgalluccio0.rpgcombat.model.User;
import io.github.dgalluccio0.rpgcombat.repository.UserRepository;
import io.github.dgalluccio0.rpgcombat.utils.Finals;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDTO toUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllDTO() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserDTO)
                .toList();
    }

    public UserDTO getByIdDTO(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        return toUserDTO(user);
    }

    public UserDTO getByUsernameDTO(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        return toUserDTO(user);
    }

    public UserDTO getByEmailDTO(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        return toUserDTO(user);
    }

    public User createUser(CreateUserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }

        User user = modelMapper.map(dto, User.class);
        return userRepository.save(user);
    }

    public User updateUser(Integer id, UpdateUserDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        if (userRepository.existsByEmail(dto.getEmail())
                && !oldUser.getEmail().equals(dto.getEmail())) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }

        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public User patchUser(Integer id, PatchUserDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        if (dto.getUsername() != null && dto.getUsername().trim().isEmpty()) {
            dto.setUsername(null);
        }

        if (dto.getEmail() != null && dto.getEmail().trim().isEmpty()) {
            dto.setEmail(null);
        }

        if (dto.getEmail() != null) {
            if (userRepository.existsByEmail(dto.getEmail())
                    && !oldUser.getEmail().equals(dto.getEmail())) {
                throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
            }
        }

        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public Boolean deleteById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        userRepository.delete(user);
        return true;
    }
}