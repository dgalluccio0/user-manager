package io.github.dgalluccio0.usermanager.dto;

import io.github.dgalluccio0.usermanager.utils.Finals;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {

    @NotBlank(message = Finals.USERNAME_REQUIRED_ERROR)
    @Size(min = Finals.MIN_USERNAME_LENGTH, max = Finals.MAX_USERNAME_LENGTH, message = Finals.USERNAME_LENGTH_ERROR)
    private String username;

    @NotBlank(message = Finals.EMAIL_REQUIRED_ERROR)
    @Email(message = Finals.EMAIL_NOT_VALID_ERROR)
    @Size(max = Finals.MAX_EMAIL_LENGTH, message = Finals.EMAIL_LENGTH_ERROR)
    private String email;

    @NotBlank(message = Finals.PASSWORD_REQUIRED_ERROR)
    @Size(min = Finals.MIN_PASSWORD_LENGTH, max = Finals.MAX_PASSWORD_LENGTH, message = Finals.PASSWORD_LENGTH_ERROR)
    private String password;
}