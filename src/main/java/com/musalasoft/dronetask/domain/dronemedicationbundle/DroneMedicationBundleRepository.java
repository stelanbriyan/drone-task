package com.musalasoft.dronetask.domain.dronemedicationbundle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneMedicationBundleRepository extends JpaRepository<DroneMedicationBundle, String> {

	Optional<DroneMedicationBundle> findByDrone_SerialNumber(String droneSerialNumber);

}
