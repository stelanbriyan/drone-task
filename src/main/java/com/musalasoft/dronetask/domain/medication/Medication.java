package com.musalasoft.dronetask.domain.medication;

import com.musalasoft.dronetask.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "medication")
public class Medication extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "weight")
	private int weight;

	@Column(name = "code", unique = true)
	private String code;

	// This field stores the URL of the image where it is located in S3
	@Column(name = "image_url")
	private String imageUrl;

}
