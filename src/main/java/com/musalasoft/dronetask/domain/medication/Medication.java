package com.musalasoft.dronetask.domain.medication;

import com.musalasoft.dronetask.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "medication")
public class Medication extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "weight")
	private int weight;

	@Column(name = "code")
	private String code;

	// This field stores the URL of the image where it is located in S3
	@Column(name = "image_url")
	private String imageUrl;

}
