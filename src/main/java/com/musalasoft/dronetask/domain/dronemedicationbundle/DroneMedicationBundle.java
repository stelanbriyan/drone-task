package com.musalasoft.dronetask.domain.dronemedicationbundle;

import com.musalasoft.dronetask.domain.BaseEntity;
import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.medication.Medication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "drone-medication-bundle")
public class DroneMedicationBundle extends BaseEntity {

	@OneToOne
	@Column(name = "drone")
	private Drone drone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "drone_medication_bundle_id")
	private List<Medication> medications;

}
