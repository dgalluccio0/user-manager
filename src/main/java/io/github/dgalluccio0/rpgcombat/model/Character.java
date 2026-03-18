package io.github.dgalluccio0.rpgcombat.model;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import io.github.dgalluccio0.rpgcombat.utils.Finals;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Character {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
    @JdbcTypeCode(SqlTypes.JSON) // va messo per essere sicuri che crei una colonna nella stessa tabella e non altre colonne
	@Size(max = Finals.MAX_MOVES)
	private List<Action> actions;
	
	@NotNull
	@PositiveOrZero
	private Integer might, speed, mind;

	@NotBlank
	@PositiveOrZero
	@Max(value = Finals.MAX_BODY_PART_HEALTH)
	private Integer leftLeg, rightLeg, leftArm, rightArm, torso, head;
	
}
