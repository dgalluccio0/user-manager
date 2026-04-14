package io.github.dgalluccio0.usermanager.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.github.dgalluccio0.usermanager.model.User;
import io.github.dgalluccio0.usermanager.repository.UserRepository;
import io.github.dgalluccio0.usermanager.utils.RoleType;

@Configuration
public class TableInitializer {
    @Bean
    public CommandLineRunner createAdminUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@galluccio.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole(RoleType.ADMIN);
                userRepository.save(admin);
            }
        };
    }
    
    @Bean
    public CommandLineRunner createUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setEmail("user@galluccio.com");
                user.setPassword(passwordEncoder.encode("user"));
                user.setRole(RoleType.USER);
                userRepository.save(user);
            }
        };
    }
}
