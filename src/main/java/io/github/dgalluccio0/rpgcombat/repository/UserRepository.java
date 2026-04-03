package io.github.dgalluccio0.rpgcombat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dgalluccio0.rpgcombat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);
}
