package com.musalasoft.dronetask.domain.medication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

}
