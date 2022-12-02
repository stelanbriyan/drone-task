package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.application.DroneService;
import com.musalasoft.dronetask.domain.drone.State;
import com.musalasoft.dronetask.dto.DroneDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/drone")
public class DroneController extends BaseController {

	private final DroneService droneService;

	public DroneController(DroneService droneService) {
		this.droneService = droneService;
	}

	/**
	 * -registering a drone
	 */
	@PostMapping
	public ResponseEntity<DroneDTO> registerDrone(@RequestBody DroneDTO drone) {
		return ResponseEntity.ok().body(this.droneService.registerDrone(drone));
	}

	/**
	 * -checking drone battery level for given drone
	 * @return
	 */
	@GetMapping("checkbattery/{serialNumber}")
	public ResponseEntity<Integer> checkDroneBatteryLevel(@PathVariable String serialNumber) {
		return ResponseEntity.ok().body(this.droneService.checkDroneBatteryLevel(serialNumber).getBatteryCapacity());
	}

	/**
	 * -checking available drones for loading
	 */
	@GetMapping("available")
	public ResponseEntity<List<DroneDTO>> getAvailableDronesForLoading() {
		return ResponseEntity.ok().body(this.droneService.getDronesByState(State.LOADING));
	}

	@GetMapping
	public ResponseEntity<List<DroneDTO>> getDrones() {
		return ResponseEntity.ok().body(this.droneService.getDrones());
	}

}
