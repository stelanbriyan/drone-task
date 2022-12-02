package com.musalasoft.dronetask.application;

import com.musalasoft.dronetask.application.exception.DroneWeightLimitExceededException;
import com.musalasoft.dronetask.application.exception.MedicationNotFoundException;
import com.musalasoft.dronetask.domain.drone.Drone;
import com.musalasoft.dronetask.domain.drone.State;
import com.musalasoft.dronetask.domain.dronemedicationbundle.DroneMedicationBundle;
import com.musalasoft.dronetask.domain.dronemedicationbundle.DroneMedicationBundleRepository;
import com.musalasoft.dronetask.domain.medication.Medication;
import com.musalasoft.dronetask.domain.medication.MedicationRepository;
import com.musalasoft.dronetask.dto.DroneMedicationBundleDTO;
import com.musalasoft.dronetask.dto.MedicationDTO;
import com.musalasoft.dronetask.infrastructure.adapter.S3Adapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class DispatchService {

	private final MedicationRepository medicationRepository;

	private final DroneMedicationBundleRepository droneMedicationBundleRepository;

	private final DroneService droneService;

	private final S3Adapter s3Adapter;

	public DispatchService(MedicationRepository medicationRepository,
			DroneMedicationBundleRepository droneMedicationBundleRepository, DroneService droneService,
			S3Adapter s3Adapter) {
		this.medicationRepository = medicationRepository;
		this.droneMedicationBundleRepository = droneMedicationBundleRepository;
		this.droneService = droneService;
		this.s3Adapter = s3Adapter;
	}

	@Transactional
	public DroneMedicationBundleDTO addMedicationToDrone(MedicationDTO medication, String droneSerialNumber) {
		DroneMedicationBundle savedEntity = this.droneMedicationBundleRepository
				.findByDrone_SerialNumber(droneSerialNumber).orElseGet(() -> {
					DroneMedicationBundle entity = new DroneMedicationBundle();
					entity.setDrone(droneService.findDroneBySerialNumberAndState(droneSerialNumber, State.LOADING));
					entity.setMedications(new ArrayList<>());
					return entity;
				});

		Drone drone = savedEntity.getDrone();
		int totalWeight = savedEntity.getMedications().stream().mapToInt(Medication::getWeight).sum()
				+ medication.getWeight();
		if (totalWeight > savedEntity.getDrone().getWeightLimit()) {
			throw new DroneWeightLimitExceededException();
		}
		else if (totalWeight == savedEntity.getDrone().getWeightLimit() || drone.getBatteryCapacity() <= 25) {
			drone.setState(State.LOADED);
		}

		savedEntity.getMedications().add(medication.toEntity());

		return DroneMedicationBundleDTO.fromEntity(this.droneMedicationBundleRepository.save(savedEntity));
	}

	@Transactional
	public MedicationDTO uploadMedicationImage(MultipartFile file, String medicationCode) {
		Medication medication = this.medicationRepository.findByCode(medicationCode)
				.orElseThrow(MedicationNotFoundException::new);

		medication.setImageUrl(s3Adapter.upload(file));
		return MedicationDTO.fromEntity(medication);
	}

	public DroneMedicationBundleDTO checkMedicationItemsByDrone(String droneSerialNumber) {
		return DroneMedicationBundleDTO.fromEntity(getDroneMedicationBundle(droneSerialNumber));
	}

	private DroneMedicationBundle getDroneMedicationBundle(String droneSerialNumber) {
		return this.droneMedicationBundleRepository.findByDrone_SerialNumber(droneSerialNumber).orElseGet(() -> {
			DroneMedicationBundle entity = new DroneMedicationBundle();
			entity.setDrone(droneService.findDroneBySerialNumber(droneSerialNumber));
			entity.setMedications(new ArrayList<>());
			return entity;
		});
	}

}
