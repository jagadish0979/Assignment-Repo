package com.assignments.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignments.constant.FileType;
import com.assignments.parser.CSVParser;
import com.assignments.parser.IParser;
import com.assignments.parser.XMLParser;

/**
 * @author Jagadish Anala
 * 
 */
@Component
public class IParserFactory {

	@Autowired
	private XMLParser xmlParser;

	@Autowired
	private CSVParser csvParser;

	/**
	 * gets the parser basing on the type of file
	 * 
	 * @return IParser
	 */
	public IParser getParser(FileType type) {
		switch (type) {
		case xml:
			return xmlParser;
		case csv:
			return csvParser;
		default:
			throw new IllegalArgumentException("No Such Parser");
		}
	}
}
