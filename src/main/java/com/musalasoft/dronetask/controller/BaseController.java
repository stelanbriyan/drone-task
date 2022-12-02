package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Slf4j
public class BaseController {

	@ExceptionHandler({ RuntimeException.class })
	public final ResponseEntity<Object> handleExceptions(Exception e, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponseDTO.builder().description(e.getMessage()).path(path).build());
	}

}
