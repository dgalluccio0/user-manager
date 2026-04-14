package io.github.dgalluccio0.usermanager.utils;

public interface Finals {
	// Numbers
	int MIN_USERNAME_LENGTH = 3;
	int MIN_PASSWORD_LENGTH = 8;

	int MAX_EMAIL_LENGTH = 256;
	int MAX_USERNAME_LENGTH = 64;
	int MAX_PASSWORD_LENGTH = 64;
	int MAX_HASHED_PASSWORD_LENGTH = 512;

	// Error messages
	String EMAIL_REQUIRED_ERROR = "Email required.";
	String EMAIL_NOT_VALID_ERROR = "Email not valid.";
	String EMAIL_ALREADY_EXISTS_ERROR = "Email already exists.";
	String EMAIL_LENGTH_ERROR = "Email must be at most " + MAX_EMAIL_LENGTH + " characters.";

	String USERNAME_REQUIRED_ERROR = "Username required.";
	String USERNAME_LENGTH_ERROR = "Username must be at least " + MIN_USERNAME_LENGTH + " characters, and at most " + MAX_USERNAME_LENGTH + " characters.";

	String PASSWORD_REQUIRED_ERROR = "Password required.";
	String PASSWORD_LENGTH_ERROR = "Password must be at least " + MIN_PASSWORD_LENGTH + " characters, and at most " + MAX_PASSWORD_LENGTH + " characters.";

	String OLD_PASSWORD_NOT_MATCHING_ERROR = "The old password is not correct.";

	String ROLE_REQUIRED_ERROR = "Role required.";

	String CANT_DEMOTE_LAST_ADMIN_ERROR = "At least one " + RoleType.ADMIN + " must exist.";
	
	String USER_NOT_FOUND_ERROR = "User not found.";
}

