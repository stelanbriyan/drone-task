package com.musalasoft.dronetask.application.exception;

public class MedicationNotFoundException extends RuntimeException {

	public MedicationNotFoundException() {
		super("Medication Not Found Exception");
	}

}
