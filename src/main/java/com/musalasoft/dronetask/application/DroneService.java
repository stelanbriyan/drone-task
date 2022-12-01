package com.musalasoft.dronetask.application;

import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.drone.DroneRepository;
import com.musalasoft.dronetask.dto.DroneDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DroneService {

	private final DroneRepository droneRepository;

	public DroneService(DroneRepository droneRepository) {
		this.droneRepository = droneRepository;
	}

	public DroneDTO registerDrone(DroneDTO drone) {
		Drone entity = drone.toEntity();

		return DroneDTO.fromEntity(this.droneRepository.save(entity));
	}

	public List<DroneDTO> getDrones() {
		return this.droneRepository.findAll().stream().map(DroneDTO::fromEntity).toList();
	}

}
