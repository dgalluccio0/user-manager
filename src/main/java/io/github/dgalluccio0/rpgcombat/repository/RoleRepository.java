package io.github.dgalluccio0.rpgcombat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dgalluccio0.rpgcombat.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
