package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.application.DispatchService;
import com.musalasoft.dronetask.dto.DroneMedicationBundleDTO;
import com.musalasoft.dronetask.dto.MedicationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("v1/dispatch")
public class DispatchController {

	private final DispatchService dispatchService;

	public DispatchController(DispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	@PutMapping("/{droneSerialNumber}")
	public ResponseEntity<DroneMedicationBundleDTO> addMedicationToDrone(@RequestBody MedicationDTO medication,
			@PathVariable String droneSerialNumber) {
		return ResponseEntity.ok().body(this.dispatchService.addMedicationToDrone(medication, droneSerialNumber));
	}

	@PutMapping("/medication/image/upload/{medicationCode}")
	public ResponseEntity<MedicationDTO> uploadMedicationImage(@RequestParam MultipartFile file,
			@PathVariable String medicationCode) {
		return ResponseEntity.ok().body(this.dispatchService.uploadMedicationImage(file, medicationCode));
	}

	@PutMapping
	public void checkMedicationItem() {

	}

}
