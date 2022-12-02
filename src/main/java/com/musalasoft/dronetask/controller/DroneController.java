package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.application.DroneService;
import com.musalasoft.dronetask.dto.DroneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/drone")
public class DroneController {

	private final DroneService droneService;

	public DroneController(DroneService droneService) {
		this.droneService = droneService;
	}

	@PostMapping
	public ResponseEntity<DroneDTO> registerDrone(@RequestBody DroneDTO drone) {
		return ResponseEntity.ok().body(this.droneService.registerDrone(drone));
	}

	@GetMapping
	public void getAvailableDronesForLoading() {

	}

	@GetMapping
	public ResponseEntity<List<DroneDTO>> getDrones() {
		return ResponseEntity.ok().body(this.droneService.getDrones());
	}

	@GetMapping
	public void checkDroneBatteryLevel() {

	}

}
