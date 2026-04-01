package io.github.dgalluccio0.rpgcombat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.dgalluccio0.rpgcombat.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
