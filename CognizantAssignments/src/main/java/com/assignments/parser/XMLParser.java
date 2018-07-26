package com.assignments.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignments.domain.CustomerStatement;
import com.assignments.exception.XMLException;
import com.assignments.service.AssignmentService;

@Component
public class XMLParser implements IParser {

	@Autowired
	private AssignmentService assignmentService;

	/**
	 * parses the given fileContent.
	 * 
	 * @return CustomerStatement[]
	 */
	@Override
	public CustomerStatement[] parseFile(String fileContent) throws XMLException {
		return assignmentService.processXML(fileContent);
	}

}
