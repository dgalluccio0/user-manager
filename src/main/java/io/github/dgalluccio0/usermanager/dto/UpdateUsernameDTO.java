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
public class UpdateUsernameDTO {;
	@NotBlank(message = Finals.USERNAME_REQUIRED_ERROR)
	@Size(min = Finals.MIN_USERNAME_LENGTH, max = Finals.MAX_USERNAME_LENGTH, message = Finals.USERNAME_LENGTH_ERROR)
	private String username;
}