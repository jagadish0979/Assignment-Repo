package com.assignments.service;

import com.assignments.domain.CustomerRecord;


/**
 * @author Jagadish Anala
 *
 */
public interface XmlToObjectService {

	public CustomerRecord getXmlRecords(String xmlFileContent);
}
