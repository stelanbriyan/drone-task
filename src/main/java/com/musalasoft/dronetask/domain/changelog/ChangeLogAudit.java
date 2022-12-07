package com.musalasoft.dronetask.domain.changelog;

import com.musalasoft.dronetask.application.changelog.ChangeLogType;
import com.musalasoft.dronetask.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "change_log_audit")
public class ChangeLogAudit extends BaseEntity {

	@Column(name = "reference")
	private String reference;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private ChangeLogType changeLogType;

}
