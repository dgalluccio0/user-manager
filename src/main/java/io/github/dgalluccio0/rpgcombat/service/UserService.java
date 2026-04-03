package io.github.dgalluccio0.rpgcombat.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.dgalluccio0.rpgcombat.dto.CreateUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.PatchUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.UpdateUserDTO;
import io.github.dgalluccio0.rpgcombat.dto.UserDTO;
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
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllDTO() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserDTO)
                .toList();
    }

    public UserDTO getByIdDTO(Integer id) {
        return userRepository.findById(id)
                .map(this::toUserDTO)
                .orElse(null);
    }

    public UserDTO getByUsernameDTO(String username) {
        return userRepository.findByUsername(username)
                .map(this::toUserDTO)
                .orElse(null);
    }

    public UserDTO getByEmailDTO(String email) {
        return userRepository.findByEmail(email)
                .map(this::toUserDTO)
                .orElse(null);
    }

    public User createUser(CreateUserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }

        User user = modelMapper.map(dto, User.class);
        return userRepository.save(user);
    }

    public User updateUser(Integer id, UpdateUserDTO dto) {
        User oldUser = userRepository.findById(id).orElse(null);
        if (oldUser == null) {
            return null;
        }

        if (userRepository.existsByEmail(dto.getEmail())
                && !oldUser.getEmail().equals(dto.getEmail())) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }

        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public User patchUser(Integer id, PatchUserDTO dto) {
        User oldUser = userRepository.findById(id).orElse(null);
        if (oldUser == null) {
            return null;
        }

        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            if (userRepository.existsByEmail(dto.getEmail())
                    && !oldUser.getEmail().equals(dto.getEmail())) {
                throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
            }
        }

        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public Boolean deleteById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }
}