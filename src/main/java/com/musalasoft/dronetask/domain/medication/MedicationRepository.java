package com.musalasoft.dronetask.domain.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

	Optional<Medication> findByCode(String code);

}
