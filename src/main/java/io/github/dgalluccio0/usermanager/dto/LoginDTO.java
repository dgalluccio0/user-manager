package io.github.dgalluccio0.usermanager.dto;

import io.github.dgalluccio0.usermanager.utils.Finals;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
	@NotBlank(message = Finals.EMAIL_REQUIRED_ERROR)
    @Email(message = Finals.EMAIL_NOT_VALID_ERROR)
    private String email;

    @NotBlank(message = Finals.PASSWORD_REQUIRED_ERROR)
    private String password;
}
