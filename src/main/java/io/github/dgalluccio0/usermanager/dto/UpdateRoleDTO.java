package io.github.dgalluccio0.usermanager.dto;

import io.github.dgalluccio0.usermanager.utils.Finals;
import io.github.dgalluccio0.usermanager.utils.RoleType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleDTO {
	@NotNull(message = Finals.ROLE_REQUIRED_ERROR)
	private RoleType role;
}
