package com.moskalev.domain.entity;

import com.moskalev.domain.entity.status.StatusOfCargo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.EnumType.STRING;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Cargo
 */
@Getter
@Setter
@Table(name = "cargo")
@Entity
public class Cargo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**unique value*/
    @Column
    private String numberOfCargo;

    @Column
    private String nameOfCargo;

    /**
     * weight in kilograms
     */
    @Column
    private Double weight;

    @Column
    @Enumerated(STRING)
    private StatusOfCargo statusOfCargo;

    @OneToMany(mappedBy = "currentCargoForWayPoint",
            orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<WayPoint> wayPointList;
}
