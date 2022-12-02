package com.musalasoft.dronetask.application.exception;

public class DroneWeightLimitExceededException extends RuntimeException {

	public DroneWeightLimitExceededException() {
		super("Drone Weight Limit Exceeded");
	}

}
