package io.github.dgalluccio0.usermanager.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.dgalluccio0.usermanager.dto.CreateUserDTO;
import io.github.dgalluccio0.usermanager.dto.PatchUserDTO;
import io.github.dgalluccio0.usermanager.dto.ResetPasswordDTO;
import io.github.dgalluccio0.usermanager.dto.UpdateEmailDTO;
import io.github.dgalluccio0.usermanager.dto.UpdatePasswordDTO;
import io.github.dgalluccio0.usermanager.dto.UpdateRoleDTO;
import io.github.dgalluccio0.usermanager.dto.UpdateUserDTO;
import io.github.dgalluccio0.usermanager.dto.UpdateUsernameDTO;
import io.github.dgalluccio0.usermanager.dto.UserDTO;
import io.github.dgalluccio0.usermanager.exceptions.ResourceNotFoundException;
import io.github.dgalluccio0.usermanager.model.User;
import io.github.dgalluccio0.usermanager.repository.UserRepository;
import io.github.dgalluccio0.usermanager.utils.Finals;
import io.github.dgalluccio0.usermanager.utils.RoleType;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO toUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User createUser(CreateUserDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }

        User user = modelMapper.map(dto, User.class);

        if (!(dto.getPassword().equals(dto.getConfirmationPassword()))) {
            throw new IllegalArgumentException(Finals.CONFIRMATION_PASSWORD_NOT_MATCHING_ERROR);
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(RoleType.USER);

        return userRepository.save(user);
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserDTO)
                .toList();
    }

    public UserDTO getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        return toUserDTO(user);
    }

    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        return toUserDTO(user);
    }

    public UserDTO getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        return toUserDTO(user);
    }

    public User updateUser(Integer id, UpdateUserDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }

        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public User patchUser(Integer id, PatchUserDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        if (null != dto.getEmail()
                && userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }

        if (null != dto.getUsername().trim()) {
            oldUser.setUsername(dto.getUsername());
        }

        if (null != dto.getEmail().trim()) {
            oldUser.setEmail(dto.getEmail());
        }
        
        return userRepository.save(oldUser);
    }
    

    public User updateRoleUser(Integer id, UpdateRoleDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));
        if (dto.getRole() == RoleType.USER && oldUser.getRole() == RoleType.ADMIN
                && userRepository.countByRole(RoleType.ADMIN) <= 1) {
            throw new IllegalStateException(Finals.AT_LEAST_ONE_ADMIN_NEEDED_ERROR);
        }

        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public User updateUsername(Integer id, UpdateUsernameDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public User updateEmail(Integer id, UpdateEmailDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new IllegalArgumentException(Finals.EMAIL_ALREADY_EXISTS_ERROR);
        }
        
        modelMapper.map(dto, oldUser);
        return userRepository.save(oldUser);
    }

    public User updatePassword(Integer id, UpdatePasswordDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));
        if (!(passwordEncoder.matches(dto.getOldPassword(), oldUser.getPassword()))) {
            throw new IllegalArgumentException(Finals.OLD_PASSWORD_NOT_MATCHING_ERROR);
        }

        if (!(dto.getNewPassword().equals(dto.getConfirmationPassword()))) {
            throw new IllegalArgumentException(Finals.CONFIRMATION_PASSWORD_NOT_MATCHING_ERROR);
        }

        oldUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        return userRepository.save(oldUser);
    }

    public User resetPassword(Integer id, ResetPasswordDTO dto) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));

        oldUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(oldUser);
    }

    public void deleteById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Finals.USER_NOT_FOUND_ERROR));
        if (userRepository.countByRole(RoleType.ADMIN) <= 1 && user.getRole() == RoleType.ADMIN) {
            throw new IllegalStateException(Finals.AT_LEAST_ONE_ADMIN_NEEDED_ERROR);
        }
        userRepository.delete(user);
    }
}