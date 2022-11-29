package com.musalasoft.dronetask.domain.drone;

import com.musalasoft.dronetask.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "drone")
public class Drone extends BaseEntity {

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit")
    private int weightLimit;

    @Column(name = "battery_capacity")
    private int batteryCapacity;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

}
