package com.assignments.service;

import com.assignments.domain.CustomerStatement;
import com.assignments.exception.CSVException;
import com.assignments.exception.XMLException;


/**
 * @author Jagadish Anala
 *
 */
public interface AssignmentService {

	public CustomerStatement[] processCSV(String fileContent) throws CSVException ;

	public CustomerStatement[] processXML(String fileContent) throws XMLException;
}
