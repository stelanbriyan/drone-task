package com.musalasoft.dronetask.domain.dronemedicationbundle;

import com.musalasoft.dronetask.domain.BaseEntity;
import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.medication.Medication;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "drone_medication_bundle")
public class DroneMedicationBundle extends BaseEntity {

	@OneToOne(cascade = CascadeType.ALL)
	private Drone drone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "drone_medication_bundle_id")
	private List<Medication> medications;

}
