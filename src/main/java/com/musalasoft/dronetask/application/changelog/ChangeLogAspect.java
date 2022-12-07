package com.musalasoft.dronetask.application.changelog;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.musalasoft.dronetask.domain.changelog.ChangeLogAudit;
import com.musalasoft.dronetask.domain.changelog.ChangeLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ChangeLogAspect {

	private final ObjectMapper mapper;

	private final ChangeLogRepository changeLogRepository;

	public ChangeLogAspect(ChangeLogRepository changeLogRepository) {
		this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
		this.changeLogRepository = changeLogRepository;
	}

	@AfterReturning("@annotation(ChangeLog)")
	public void after(JoinPoint joinPoint) {
		ChangeLogAudit audit = new ChangeLogAudit();

		if (joinPoint.getArgs().length > 0) {
			JsonNode node = mapper.convertValue(joinPoint.getArgs()[0], JsonNode.class);
			if (node.has("id")) {
				audit.setReference(node.get("id").asText());
			}
			audit.setDescription(node.toPrettyString());
		}

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		if (signature != null) {
			audit.setChangeLogType(signature.getMethod().getAnnotation(ChangeLog.class).type());
		}
		changeLogRepository.save(audit);
	}

}
