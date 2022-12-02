package com.musalasoft.dronetask.controller;

import com.musalasoft.dronetask.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@Slf4j
public class BaseController {

	@ExceptionHandler({ RuntimeException.class, MethodArgumentNotValidException.class })
	public final ResponseEntity<Object> handleExceptions(Exception e, WebRequest request, Errors errors) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();

		if (errors.hasErrors()) {
			List<Map<String, String>> validationError = errors.getFieldErrors().stream()
					.map(er -> Map.of("field", er.getField(), "message", er.getDefaultMessage())).toList();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ErrorResponseDTO.builder().description(validationError).path(path).build());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponseDTO.builder().description(e.getMessage()).path(path).build());
	}

}
