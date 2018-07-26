package com.assignments.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.assignments.domain.CustomerRecord;
import com.assignments.service.XmlToObjectService;


/**
 * @author Jagadish Anala
 *
 */
@Service("XmlToObjectService")
public class XmlToObjectServiceImpl implements XmlToObjectService {
	private static final Logger logger = Logger.getLogger(XmlToObjectServiceImpl.class.getName());

	@Override
	public CustomerRecord getXmlRecords(String xmlFileContent) {
		CustomerRecord customerRecord = null;
		try {
			logger.info("Inside getXmlRecords *******!");
	        File file = writeUsingFileWriter("records.xml", xmlFileContent);
	        
			JAXBContext jaxbContext = JAXBContext.newInstance(CustomerRecord.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			customerRecord = (CustomerRecord) jaxbUnmarshaller.unmarshal(file);
			logger.info("customerRecord=========>"+customerRecord);			
			return customerRecord;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
			return customerRecord;
		}
	}

	private File writeUsingFileWriter(String filePath, String fileContent) {
		File file = null;
		try {
			file = new File(filePath);
			OutputStream outputStream = new FileOutputStream(file);
			OutputStreamWriter bufferedWriter = new OutputStreamWriter(outputStream, "UTF8");
			bufferedWriter.write(fileContent);
			bufferedWriter.close();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return file;
		}

	}
}
