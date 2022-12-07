package com.musalasoft.dronetask.domain.dronemedicationbundle;

import com.musalasoft.dronetask.application.changelog.ChangeLog;
import com.musalasoft.dronetask.application.changelog.ChangeLogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneMedicationBundleRepository extends JpaRepository<DroneMedicationBundle, String> {

	Optional<DroneMedicationBundle> findByDrone_SerialNumber(String droneSerialNumber);

	@Override
	@ChangeLog(type = ChangeLogType.Medication)
	<S extends DroneMedicationBundle> S save(S entity);

}
