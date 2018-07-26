package com.assignments.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignments.domain.CustomerStatement;
import com.assignments.exception.CSVException;
import com.assignments.service.AssignmentService;

/**
 * @author Jagadish Anala
 *
 */
@Component
public class CSVParser implements IParser {

	@Autowired
	private AssignmentService assignmentService;

	/**
	 * parses the given fileContent.
	 * 
	 * @return CustomerStatement[]
	 */
	@Override
	public CustomerStatement[] parseFile(String fileContent) throws CSVException {
		return assignmentService.processCSV(fileContent);
	}

}
