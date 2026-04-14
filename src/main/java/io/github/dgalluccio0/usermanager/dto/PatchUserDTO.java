package io.github.dgalluccio0.usermanager.dto;

import io.github.dgalluccio0.usermanager.utils.Finals;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserDTO {
	@Size(max = Finals.MAX_USERNAME_LENGTH, message = Finals.USERNAME_TOO_LONG_ERROR)
	private String username;
	
	@Email(message = Finals.EMAIL_NOT_VALID_ERROR)
	@Size(max = Finals.MAX_EMAIL_LENGTH, message = Finals.EMAIL_TOO_LONG_ERROR)
	private String email;
}