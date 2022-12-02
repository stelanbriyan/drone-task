package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.application.DroneService;
import com.musalasoft.dronetask.domain.drone.State;
import com.musalasoft.dronetask.dto.DroneDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
	@ApiOperation(value = "Registering a drone", response = DroneDTO.class)
	@PostMapping
	public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO drone) {
		return ResponseEntity.ok().body(this.droneService.registerDrone(drone));
	}

	/**
	 * -checking drone battery level for given drone
	 * @return
	 */
	@ApiOperation(value = "Checking drone battery level for the given drone", response = Integer.class)
	@GetMapping("checkbattery/{serialNumber}")
	public ResponseEntity<Integer> checkDroneBatteryLevel(@PathVariable String serialNumber) {
		return ResponseEntity.ok().body(this.droneService.checkDroneBatteryLevel(serialNumber).getBatteryCapacity());
	}

	/**
	 * -checking available drones for loading
	 */
	@ApiOperation(value = "Checking available drones for loading", response = List.class)
	@GetMapping("available")
	public ResponseEntity<List<DroneDTO>> getAvailableDronesForLoading() {
		return ResponseEntity.ok().body(this.droneService.getDronesByState(State.LOADING));
	}

	@ApiOperation(value = "Get all drones", response = List.class)
	@GetMapping
	public ResponseEntity<List<DroneDTO>> getDrones() {
		return ResponseEntity.ok().body(this.droneService.getDrones());
	}

}
