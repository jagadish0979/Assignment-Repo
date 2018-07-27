package com.assignments.controller;

import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignments.constant.FileType;
import com.assignments.domain.CustomerStatement;
import com.assignments.domain.FileModel;
import com.assignments.factory.IParserFactory;
import com.assignments.parser.IParser;
import com.assignments.validator.FileValidator;

/**
 * @author Jagadish Anala
 *
 */
@Controller
@RequestMapping("/")
public class AssignmentController {
	private static final Logger logger = Logger.getLogger(AssignmentController.class.getName());

	@Autowired
	private IParserFactory parserFactory;

	@Autowired
	private FileValidator fileValidator;
	/**
	 * Process uploaded files.
	 * @return ResponseEntity<CustomerStatement[]>
	 */
	@PostMapping(value = "/processFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerStatement[]> processFile(@RequestBody FileModel fileModel) throws Exception {
		
		fileValidator.validate(fileModel);
		
		String fileContents = StringUtils.EMPTY;
		logger.info("fileName     [ " + fileModel.getFileName() + " ]");
		logger.info("fileContents [ " + fileModel.getFileContent() + " ]");
		logger.info("fileType     [ " + fileModel.getFileType() + " ]");
		
		fileContents = fileModel.getFileContent();
		IParser parser = null;
		if(FileType.csv.toString().equals(fileModel.getFileType())){
			parser = parserFactory.getParser(FileType.csv);
		}
		else if(FileType.xml.toString().equals(fileModel.getFileType())){
			parser = parserFactory.getParser(FileType.xml);
		}
		else {
			throw new IllegalArgumentException("Invalid File");
		}
		CustomerStatement[] customerStatements = parser.parseFile(fileContents);
		return new ResponseEntity<CustomerStatement[]>(customerStatements, HttpStatus.OK);
	}
}
