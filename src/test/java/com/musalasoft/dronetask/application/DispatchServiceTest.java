package com.musalasoft.dronetask.application;

import com.musalasoft.dronetask.application.exception.DroneWeightLimitExceededException;
import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.dronemedicationbundle.DroneMedicationBundle;
import com.musalasoft.dronetask.domain.dronemedicationbundle.DroneMedicationBundleRepository;
import com.musalasoft.dronetask.domain.medication.MedicationRepository;
import com.musalasoft.dronetask.dto.DroneDTO;
import com.musalasoft.dronetask.dto.DroneMedicationBundleDTO;
import com.musalasoft.dronetask.dto.MedicationDTO;
import com.musalasoft.dronetask.infrastructure.adapter.S3Adapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DispatchServiceTest {

	@InjectMocks
	private DispatchService dispatchService;

	@Mock
	private MedicationRepository medicationRepository;

	@Mock
	private DroneMedicationBundleRepository droneMedicationBundleRepository;

	@Mock
	private DroneService droneService;

	@Mock
	private S3Adapter s3Adapter;

	MedicationDTO medicationDTO;

	DroneMedicationBundle droneMedicationBundle;

	@BeforeEach
	public void before() {
		medicationDTO = MedicationDTO.builder().code("1").weight(10).build();

		Drone drone = new Drone();
        drone.setWeightLimit(30);
		drone.setSerialNumber("D1");
		droneMedicationBundle = new DroneMedicationBundle();
		droneMedicationBundle.setDrone(drone);
		droneMedicationBundle.setMedications(new ArrayList<>());
	}

	@Test
	void addMedicationToDrone_success() {
		when(droneMedicationBundleRepository.findByDrone_SerialNumber(anyString()))
				.thenReturn(Optional.of(droneMedicationBundle));
		when(droneMedicationBundleRepository.save(any())).thenReturn(droneMedicationBundle);

		DroneMedicationBundleDTO drone = this.dispatchService.addMedicationToDrone(medicationDTO, "D1");

		assertEquals(drone.getMedications().size(), 1);
	}

	@Test
	void addMedicationToDrone_medicationBundleNotFound() {
		when(droneMedicationBundleRepository.findByDrone_SerialNumber(anyString())).thenReturn(Optional.empty());
		when(droneMedicationBundleRepository.save(any())).thenReturn(droneMedicationBundle);
		when(droneService.findDroneBySerialNumberAndState(anyString(), any())).thenReturn(
				DroneDTO.builder().serialNumber("D1").weightLimit(30).batteryCapacity(100).build().toEntity());

		DroneMedicationBundleDTO drone = this.dispatchService.addMedicationToDrone(medicationDTO, "D1");

		assertNotNull(drone);
	}

	@Test
	void addMedicationToDrone_weightLimitExceeded() {
		when(droneMedicationBundleRepository.findByDrone_SerialNumber(anyString())).thenReturn(Optional.empty());
		when(droneMedicationBundleRepository.save(any())).thenReturn(droneMedicationBundle);
		when(droneService.findDroneBySerialNumberAndState(anyString(), any())).thenReturn(
				DroneDTO.builder().serialNumber("D1").weightLimit(5).batteryCapacity(100).build().toEntity());

		assertThrows(DroneWeightLimitExceededException.class,
				() -> this.dispatchService.addMedicationToDrone(medicationDTO, "D1"));
	}

}
