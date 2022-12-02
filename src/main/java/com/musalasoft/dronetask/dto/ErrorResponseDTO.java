package com.musalasoft.dronetask.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDTO {

	private String path;

	private Object description;

}
