package com.musalasoft.dronetask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musalasoft.dronetask.application.DispatchService;
import com.musalasoft.dronetask.dto.DroneMedicationBundleDTO;
import com.musalasoft.dronetask.dto.MedicationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DispatchControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	DispatchService dispatchService;

	DroneMedicationBundleDTO droneMedicationBundleDTO;

	private String baseUrl = "/v1/dispatch";

	MedicationDTO medicationDTO;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void before() {
		droneMedicationBundleDTO = DroneMedicationBundleDTO.builder().build();
		droneMedicationBundleDTO.setId("1");
	}

	@Test
	void addMedicationToDrone_success() throws Exception {
		medicationDTO = MedicationDTO.builder().code("DEW_AE09").name("Sample_Medication").weight(10).build();
		String json = objectMapper.writeValueAsString(medicationDTO);

		doReturn(droneMedicationBundleDTO).when(dispatchService).addMedicationToDrone(medicationDTO, "D1");

		mockMvc.perform(post(baseUrl.concat("/D1")).content(json).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"));
	}

	@Test
	void addMedicationToDrone_failure() throws Exception {
		medicationDTO = MedicationDTO.builder().code("dEW_AE09").name("Sample_Medication").weight(10).build();
		String json = objectMapper.writeValueAsString(medicationDTO);

		doReturn(droneMedicationBundleDTO).when(dispatchService).addMedicationToDrone(medicationDTO, "D1");

		mockMvc.perform(post(baseUrl.concat("/D1")).content(json).contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	void checkMedicationItemsByDrone_success() throws Exception {
		doReturn(droneMedicationBundleDTO).when(dispatchService).checkMedicationItemsByDrone("D1");

		mockMvc.perform(get(baseUrl.concat("/medications/D1")).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"));
	}

	@Test
	void uploadMedicationImage_success() throws Exception {
		medicationDTO = MedicationDTO.builder().code("DEW_AE09").name("Sample_Medication").weight(10).build();
		MockMultipartFile file = new MockMultipartFile("file", "image.jpg", MediaType.IMAGE_JPEG_VALUE,
				"Hello, World!".getBytes());

		doReturn(medicationDTO).when(dispatchService).uploadMedicationImage(any(), anyString());

		mockMvc.perform(multipart(baseUrl.concat("/medication/image/upload/D1")).file(file)
				.contentType(MediaType.MULTIPART_FORM_DATA)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("DEW_AE09"));
	}

	@Test
	void uploadMedicationImage_failure() throws Exception {
		medicationDTO = MedicationDTO.builder().code("DEW_AE09").name("Sample_Medication").weight(10).build();
		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
				"Hello, World!".getBytes());

		doReturn(medicationDTO).when(dispatchService).uploadMedicationImage(any(), anyString());

		mockMvc.perform(multipart(baseUrl.concat("/medication/image/upload/D1")).file(file)
				.contentType(MediaType.MULTIPART_FORM_DATA)).andDo(print()).andExpect(status().isBadRequest());
	}

}
