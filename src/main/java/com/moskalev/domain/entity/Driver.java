package com.moskalev.domain.entity;

import com.moskalev.domain.entity.status.StatusOfDriver;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

/**
 * @author Vasiliy  Moskalev
 * @version 1.1
 * @since 03.03.22
 * This is class describes Driver
 */
@Getter
@Setter
@Table(name = "driver")
@Entity
public class Driver {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String personalNumber;

    //	Отработано часов в этом месяце
    @Column
    private Double hoursWorkedPerMonth;

    @Column
    @Enumerated(STRING)
    private StatusOfDriver statusOfDriver;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "city_id_for_driver")
    private City currentCityforDriver;

    @OneToOne(fetch = LAZY, mappedBy = "driver", optional = false)
    private Truck currentTruck;

//	водитель не выполняет сейчас другие заказы-требование в условии;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
    private Order orderForDriver;
}
