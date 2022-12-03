package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.application.DispatchService;
import com.musalasoft.dronetask.dto.DroneMedicationBundleDTO;
import com.musalasoft.dronetask.dto.MedicationDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/dispatch")
public class DispatchController extends BaseController {

	private final DispatchService dispatchService;

	public DispatchController(DispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	/**
	 * -loading a drone with medication item
	 */
	@ApiOperation(value = "Loading a drone with medication item", response = List.class)
	@PostMapping("/{droneSerialNumber}")
	public ResponseEntity<DroneMedicationBundleDTO> addMedicationToDrone(@Valid @RequestBody MedicationDTO medication,
			@PathVariable String droneSerialNumber) {
		return ResponseEntity.ok().body(this.dispatchService.addMedicationToDrone(medication, droneSerialNumber));
	}

	/**
	 * -upload medication image
	 */
	@ApiOperation(value = "Upload medication image", response = List.class)
	@PostMapping("/medication/image/upload/{medicationCode}")
	public ResponseEntity<MedicationDTO> uploadMedicationImage(@RequestParam MultipartFile file,
			@PathVariable String medicationCode) {
		return ResponseEntity.ok().body(this.dispatchService.uploadMedicationImage(file, medicationCode));
	}

	/**
	 * -checking loaded medication items for a given drone
	 */
	@ApiOperation(value = "Checking loaded medication items for a given drone", response = List.class)
	@GetMapping("/medications/{droneSerialNumber}")
	public ResponseEntity<DroneMedicationBundleDTO> checkMedicationItemsByDrone(
			@PathVariable String droneSerialNumber) {
		return ResponseEntity.ok().body(this.dispatchService.checkMedicationItemsByDrone(droneSerialNumber));
	}

}
