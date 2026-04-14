package io.github.dgalluccio0.usermanager.dto;

import io.github.dgalluccio0.usermanager.utils.Finals;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDTO {
    @NotBlank(message = Finals.PASSWORD_REQUIRED_ERROR)
    @Size(min = Finals.MIN_PASSWORD_LENGTH, max = Finals.MAX_PASSWORD_LENGTH, message = Finals.PASSWORD_LENGTH_ERROR)
    private String password;
}