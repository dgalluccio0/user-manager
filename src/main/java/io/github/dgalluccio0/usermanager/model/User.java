package io.github.dgalluccio0.usermanager.model;

import io.github.dgalluccio0.usermanager.utils.Finals;
import io.github.dgalluccio0.usermanager.utils.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Email
	@Size(max = Finals.MAX_EMAIL_LENGTH)
	private String email;
	
	@NotBlank
	@Size(max = Finals.MAX_USERNAME_LENGTH)
	private String username;
	
	@NotBlank
	@Size(max = Finals.MAX_HASHED_PASSWORD_LENGTH)
	private String password;

	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@PrePersist
    @PreUpdate
	public void normalize() {
		if (email != null) email = email.trim();
		if (username != null) username = username.trim();
	}
	
}
