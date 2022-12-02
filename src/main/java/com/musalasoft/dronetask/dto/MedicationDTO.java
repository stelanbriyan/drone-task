package com.musalasoft.dronetask.dto;

import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.medication.Medication;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MedicationDTO {

	private String name;

	private int weight;

	private String code;

	public Medication toEntity() {
		Medication medication = new Medication();
		medication.setCode(code);
		medication.setName(name);
		medication.setCode(code);
		return medication;
	}

	public static MedicationDTO fromEntity(Medication medication) {
		return MedicationDTO.builder().name(medication.getName()).weight(medication.getWeight())
				.code(medication.getCode()).build();
	}

}
