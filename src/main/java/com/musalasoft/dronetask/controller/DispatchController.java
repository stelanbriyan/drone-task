package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.application.DispatchService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/dispatch")
public class DispatchController {

	private final DispatchService dispatchService;

	public DispatchController(DispatchService dispatchService) {
		this.dispatchService = dispatchService;
	}

	@PutMapping
	public void addMedicationToDrone() {

	}

	@PutMapping
	public void checkMedicationItem() {

	}

}
