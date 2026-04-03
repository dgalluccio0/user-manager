package io.github.dgalluccio0.rpgcombat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserDTO {
	private String username;
	private String email;
}