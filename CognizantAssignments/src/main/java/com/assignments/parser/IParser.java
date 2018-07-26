package com.assignments.parser;

import com.assignments.domain.CustomerStatement;

public interface IParser {
	public CustomerStatement[] parseFile(String fileContent) throws Exception;
}
