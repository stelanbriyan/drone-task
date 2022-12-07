package com.musalasoft.dronetask.domain.changelog;

import com.musalasoft.dronetask.domain.medication.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChangeLogRepository extends JpaRepository<ChangeLogAudit, String> {

}
