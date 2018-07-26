package com.assignments.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.assignments.constant.FileType;
import com.assignments.domain.FileModel;

@Component
public class FileValidator {

	@Autowired
	private Environment env;
	
	public void validate(FileModel fileModel) throws Exception{
		if (fileModel == null) {
			throw new Exception(env.getProperty("error.file.invalid"));
		}

		if (!FileType.csv.toString().equals(fileModel.getFileType())
				&& !FileType.xml.toString().equals(fileModel.getFileType())) {
			throw new Exception(env.getProperty("error.file.invalid"));
		}
		String fileContent = fileModel.getFileContent();
		fileContent = fileContent.replaceAll("\"", "").trim();

		if (fileContent.isEmpty()) {
			throw new Exception(env.getProperty("error.file.empty"));
		}

	}
}