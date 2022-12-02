package com.musalasoft.dronetask.dto;

import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.drone.Model;
import com.musalasoft.dronetask.domain.drone.State;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Builder
@Data
public class DroneDTO {

	@Size(max = 2, message = "Must not Exceed 100 Characters in Length")
	private String serialNumber;

	private Model model;

	@Min(value = 1, message = "Must be Greater Than 1g")
	@Max(value = 500, message = "Must not Exceed 500g")
	private Integer weightLimit;

	@Min(value = 0, message = "Must be Greater Than 0%")
	@Max(value = 100, message = "Must not Exceed 100%")
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
