package com.musalasoft.dronetask.application;

import com.musalasoft.dronetask.application.exception.DroneNotFoundException;
import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.drone.DroneRepository;
import com.musalasoft.dronetask.domain.drone.State;
import com.musalasoft.dronetask.dto.DroneDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {

	private final DroneRepository droneRepository;

	public DroneService(DroneRepository droneRepository) {
		this.droneRepository = droneRepository;
	}

	public DroneDTO registerDrone(DroneDTO drone) {
		Drone entity = drone.toEntity();
		entity.setState(State.LOADING);
		return DroneDTO.fromEntity(this.droneRepository.save(entity));
	}

	public List<DroneDTO> getDrones() {
		return this.droneRepository.findAll().stream().map(DroneDTO::fromEntity).toList();
	}

	public Drone findDroneBySerialNumber(String serialNumber) {
		return this.droneRepository.findOneBySerialNumber(serialNumber).orElseThrow(DroneNotFoundException::new);
	}

	public Drone findDroneBySerialNumberAndState(String serialNumber, State state) {
		return this.droneRepository.findBySerialNumberAndState(serialNumber, state)
				.orElseThrow(DroneNotFoundException::new);
	}

	public List<DroneDTO> getDronesByState(State state) {
		return this.droneRepository.findAllByState(state).stream().map(DroneDTO::fromEntity).toList();
	}

	public Drone checkDroneBatteryLevel(String serialNumber) {
		return this.findDroneBySerialNumber(serialNumber);
	}

}
