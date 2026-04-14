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
public class UpdateEmailDTO {;
	@NotBlank(message = Finals.EMAIL_REQUIRED_ERROR)
	@Email(message = Finals.EMAIL_NOT_VALID_ERROR)
	@Size(max = Finals.MAX_EMAIL_LENGTH, message = Finals.EMAIL_LENGTH_ERROR)
	private String email;
}