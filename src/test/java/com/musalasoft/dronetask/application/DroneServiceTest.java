package com.musalasoft.dronetask.application;

import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.drone.DroneRepository;
import com.musalasoft.dronetask.domain.drone.State;
import com.musalasoft.dronetask.dto.DroneDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DroneServiceTest {

	@InjectMocks
	private DroneService droneService;

	@Mock
	private DroneRepository droneRepository;

	DroneDTO droneDTO;

	@BeforeEach
	public void before() {
		droneDTO = DroneDTO.builder().state(State.LOADING).serialNumber("D1").weightLimit(20).batteryCapacity(40)
				.build();
	}

	@Test
	void updateDroneStateWhenBatteryLow_success() {
		when(droneRepository.findAllByBatteryCapacityLessThanEqualAndStateEquals(anyInt(), any()))
				.thenReturn(List.of(droneDTO.toEntity()));
		when(droneRepository.updateDroneState(any(), any())).thenReturn(1);
		String[] serialNumbers = this.droneService.updateDroneStateWhenBatteryLow();
		assertEquals(serialNumbers.length, 1);
	}

	@Test
	void updateDroneStateWhenBatteryLow_failure() {
		when(droneRepository.findAllByBatteryCapacityLessThanEqualAndStateEquals(anyInt(), any()))
				.thenReturn(List.of(droneDTO.toEntity()));
		when(droneRepository.updateDroneState(any(), any())).thenReturn(0);
		String[] serialNumbers = this.droneService.updateDroneStateWhenBatteryLow();
		assertEquals(serialNumbers.length, 0);
	}

	@Test
	void checkDroneBatteryLevel_success() {
		when(droneRepository.findOneBySerialNumber(anyString())).thenReturn(Optional.of(droneDTO.toEntity()));
		Drone drone = this.droneService.checkDroneBatteryLevel("D1");
		assertEquals(drone.getBatteryCapacity(), 40);
	}

	@Test
	void registerDrone_success() {
		when(droneRepository.save(any())).thenReturn(droneDTO.toEntity());
		DroneDTO drone = this.droneService.registerDrone(droneDTO);
		assertEquals(drone.getSerialNumber(), "D1");
	}

}
