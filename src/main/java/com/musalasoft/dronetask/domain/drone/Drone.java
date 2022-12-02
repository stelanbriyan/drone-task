package com.musalasoft.dronetask.domain.drone;

import com.musalasoft.dronetask.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "drone")
public class Drone extends BaseEntity {

	@Column(name = "serial_number", unique = true)
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
