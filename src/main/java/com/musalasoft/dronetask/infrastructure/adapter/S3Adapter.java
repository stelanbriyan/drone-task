package com.musalasoft.dronetask.infrastructure.adapter;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class S3Adapter {

	private static final String BUCKET_NAME = "drone-task";

	private final AmazonS3 amazonS3;

	public S3Adapter(AmazonS3 amazonS3) {
		this.amazonS3 = amazonS3;
	}

	public String upload(MultipartFile file) {
		try {
			String folderName = "/medication/";
			amazonS3.putObject(new PutObjectRequest(BUCKET_NAME.concat(folderName), file.getOriginalFilename(),
					file.getInputStream(), null));
			return folderName.concat(file.getOriginalFilename());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return Strings.EMPTY;
	}

}
