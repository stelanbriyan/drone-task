package com.musalasoft.dronetask.infrastructure.scheduler;

import com.musalasoft.dronetask.application.DroneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j(topic = "drone-battery-check-scheduler")
@Component
public class DroneBatteryScheduler {

	private final DroneService droneService;

	public DroneBatteryScheduler(DroneService droneService) {
		this.droneService = droneService;
	}

	/**
	 * Introduce a periodic task to check drones battery levels
	 */
	@Scheduled(cron = "*/10 * * * * *")
	public void checkBatteryLevel() {
		log.info("Start -> drone battery check");
		String[] serialNumbers = droneService.updateDroneStateWhenBatteryLow();
		log.info("End -> drone battery check, Drone Serial Numbers: {}", Arrays.toString(serialNumbers));
	}

}
