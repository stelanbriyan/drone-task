package com.musalasoft.dronetask.dto;

import com.musalasoft.dronetask.domain.dronemedicationbundle.DroneMedicationBundle;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DroneMedicationBundleDTO {

	private String id;

	private DroneDTO drone;

	private List<MedicationDTO> medications;

	public static DroneMedicationBundleDTO fromEntity(DroneMedicationBundle entity) {
		return DroneMedicationBundleDTO.builder().id(entity.getId()).drone(DroneDTO.fromEntity(entity.getDrone()))
				.medications(entity.getMedications().stream().map(MedicationDTO::fromEntity).toList()).build();
	}

}
