package com.musalasoft.dronetask.dto;

import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.drone.Model;
import com.musalasoft.dronetask.domain.drone.State;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DroneDTO {

	private String serialNumber;

	private Model model;

	private int weightLimit;

	private int batteryCapacity;

	private State state;

	public Drone toEntity() {
		Drone drone = new Drone();
		drone.setSerialNumber(serialNumber);
		drone.setModel(model);
		drone.setWeightLimit(weightLimit);
		drone.setBatteryCapacity(batteryCapacity);
		drone.setState(state);
		return drone;
	}

	public static DroneDTO fromEntity(Drone drone) {
		return DroneDTO.builder().serialNumber(drone.getSerialNumber()).model(drone.getModel())
				.weightLimit(drone.getWeightLimit()).batteryCapacity(drone.getBatteryCapacity()).build();
	}

}
