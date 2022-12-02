package com.musalasoft.dronetask.domain.dronemedicationbundle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneMedicationBundleRepository extends JpaRepository<DroneMedicationBundle, String> {

}
