package com.musalasoft.dronetask.application;

import com.musalasoft.dronetask.application.exception.DroneNotFoundException;
import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.drone.DroneRepository;
import com.musalasoft.dronetask.domain.drone.State;
import com.musalasoft.dronetask.dto.DroneDTO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public String[] updateDroneStateWhenBatteryLow() {
		List<Drone> drones = this.droneRepository.findAllByBatteryCapacityLessThanEqualAndStateEquals(25,
				State.LOADING);
		String[] serialNumbers = drones.stream().map(Drone::getSerialNumber).toArray(String[]::new);
		if (this.droneRepository.updateDroneState(serialNumbers, State.LOADED) > 0) {
			return serialNumbers;
		}
		return Strings.EMPTY_ARRAY;
	}

}
