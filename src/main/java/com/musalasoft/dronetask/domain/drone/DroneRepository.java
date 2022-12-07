package com.musalasoft.dronetask.domain.drone;

import com.musalasoft.dronetask.application.changelog.ChangeLog;
import com.musalasoft.dronetask.application.changelog.ChangeLogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

	@Override
	@ChangeLog(type = ChangeLogType.Drone)
	<S extends Drone> S save(S entity);

	Optional<Drone> findOneBySerialNumber(String serialNumber);

	Optional<Drone> findBySerialNumberAndState(String serialNumber, State state);

	List<Drone> findAllByState(State state);

	List<Drone> findAllByBatteryCapacityLessThanEqualAndStateEquals(int batteryCapacity, State state);

	@Modifying
	@Query("UPDATE Drone o SET o.state = :state WHERE o.serialNumber IN :serialNumbers")
	int updateDroneState(@Param("serialNumbers") String[] serialNumbers, @Param("state") State state);

}
