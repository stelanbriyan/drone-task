package com.musalasoft.dronetask.dto;

import com.musalasoft.dronetask.domain.medication.Medication;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Builder
@Data
public class MedicationDTO {

	@Pattern(regexp = "^[a-zA-Z0-9_.-]*$", message = "Allowed Only Letters, Numbers And '-','_'. Eg: Sample_Medication")
	private String name;

	private int weight;

	@Pattern(regexp = "^[A-Z0-9_.-]*$", message = "Allowed Only Uppercase Letters, Numbers And '_'. Eg: DEW_AE09")
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
