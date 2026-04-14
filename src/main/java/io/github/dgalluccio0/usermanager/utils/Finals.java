package io.github.dgalluccio0.usermanager.utils;

import javax.management.relation.Role;

public interface Finals {
	// Numbers
	int MAX_EMAIL_LENGTH = 256;
	int MAX_USERNAME_LENGTH = 64;
	int MAX_PASSWORD_LENGTH = 64;
	int MAX_HASHED_PASSWORD_LENGTH = 512;

	// Error messages
	String EMAIL_REQUIRED_ERROR = "Email required.";
	String EMAIL_NOT_VALID_ERROR = "Email not valid.";
	String EMAIL_ALREADY_EXISTS_ERROR = "Email already exists.";
	String EMAIL_TOO_LONG_ERROR = "Email must be at most " + MAX_EMAIL_LENGTH + " characters.";

	String USERNAME_REQUIRED_ERROR = "Username required.";
	String USERNAME_TOO_LONG_ERROR = "Username must be at most " + MAX_USERNAME_LENGTH + " characters.";

	String PASSWORD_REQUIRED_ERROR = "Password required.";
	String PASSWORD_TOO_LONG_ERROR = "Password must be at most " + MAX_PASSWORD_LENGTH + " characters.";

	String ROLE_REQUIRED_ERROR = "Role required.";

	String CANT_DEMOTE_LAST_ADMIN_ERROR = "At least one " + RoleType.ADMIN + " must exist.";
	
	String USER_NOT_FOUND_ERROR = "User not found.";
}

