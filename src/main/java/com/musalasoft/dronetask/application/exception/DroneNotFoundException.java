package com.musalasoft.dronetask.application.exception;

public class DroneNotFoundException extends RuntimeException {

	public DroneNotFoundException() {
		super("Drone Not Found Exception");
	}

}
