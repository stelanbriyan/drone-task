package com.musalasoft.dronetask.domain.drone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

	Optional<Drone> findOneBySerialNumber(String serialNumber);

	Optional<Drone> findBySerialNumberAndState(String serialNumber, State state);

}
