package io.github.dgalluccio0.rpgcombat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dgalluccio0.rpgcombat.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
