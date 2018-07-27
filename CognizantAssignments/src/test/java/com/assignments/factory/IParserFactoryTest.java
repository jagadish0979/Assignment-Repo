package com.assignments.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

import com.assignments.CognizantAssignments.CognizantAssignmentsApplicationTests;
import com.assignments.constant.FileType;
import com.assignments.parser.CSVParser;
import com.assignments.parser.IParser;
import com.assignments.parser.XMLParser;

@ContextConfiguration(locations = { "classpath:/testContext.xml" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IParserFactoryTest extends CognizantAssignmentsApplicationTests {

	@Autowired
	private IParserFactory parserFactory;

	@Test
	public void testParsers() {
		assertNotNull(parserFactory);
		assertTrue(parserFactory.getParser(FileType.csv) instanceof IParser);
		assertTrue(parserFactory.getParser(FileType.xml) instanceof IParser);
	}

	@Test
	public void testCSVParser() {
		assertNotNull(parserFactory);
		assertEquals(CSVParser.class, parserFactory.getParser(FileType.csv).getClass());
	}

	@Test
	public void testXMLParser() {
		assertNotNull(parserFactory);
		assertEquals(XMLParser.class, parserFactory.getParser(FileType.xml).getClass());
	}
}
