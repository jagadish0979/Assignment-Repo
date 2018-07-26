package com.assignments.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignments.domain.CustomerRecord;
import com.assignments.domain.CustomerStatement;
import com.assignments.domain.Record;
import com.assignments.exception.CSVException;
import com.assignments.exception.XMLException;
import com.assignments.service.AssignmentService;
import com.assignments.service.XmlToObjectService;
import com.assignments.util.AssignmentsUtil;

/**
 * @author Jagadish Anala
 *
 */
@Service("assignmentService")
public class AssignmentServiceImpl implements AssignmentService {
	private static final Logger logger = Logger.getLogger(AssignmentServiceImpl.class.getName());

	@Autowired
	private XmlToObjectService xmlToObjectService;

	@Override
	public CustomerStatement[] processCSV(String fileContent) throws CSVException {
		String[] lines = getLines(fileContent);
		CustomerStatement[] customerStatements = new CustomerStatement[lines.length - 1];
		String[] headers = lines[0].split(",");
		for (int i = 1; i < lines.length; i++) {
			// split content based on comma
			String[] data = lines[i].split(",");
			if (data.length == headers.length) {
				CustomerStatement cs = new CustomerStatement();
				cs.setTransactionReference(Integer.parseInt(data[0]));
				cs.setAccountNumber(data[1]);
				cs.setDescription(data[2]);
				cs.setStartBalance(Double.parseDouble(data[3]));
				cs.setMutation(Double.parseDouble(data[4]));
				cs.setEndBalance(Double.parseDouble(data[5]));
				customerStatements[i - 1] = cs;
			}
		}
		customerStatements = validateCustomerStatements(Arrays.asList(customerStatements));
		return customerStatements;
	}

	@Override
	public CustomerStatement[] processXML(String xmlFileContent) throws XMLException {
		CustomerStatement[] customerStatements = null;
		try {
			xmlFileContent = formatXMLContent(xmlFileContent);
			CustomerRecord customerRecord = xmlToObjectService.getXmlRecords(xmlFileContent);

			if (customerRecord != null) {
				List<Record> recordList = customerRecord.getRecordList();
				if (recordList != null) {
					int size = recordList.size();
					customerStatements = new CustomerStatement[size];
					for (int i = 0; i < size; i++) {
						Record record = recordList.get(i);
						CustomerStatement cs = new CustomerStatement();
						cs.setTransactionReference(record.getReference());
						cs.setAccountNumber(record.getAccountNumber());
						cs.setDescription(record.getDescription());
						cs.setStartBalance(record.getStartBalance());
						cs.setMutation(record.getMutation());
						cs.setEndBalance(record.getEndBalance());
						customerStatements[i] = cs;
					}
					customerStatements = validateCustomerStatements(Arrays.asList(customerStatements));
					return customerStatements;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return customerStatements;
		}
	}

	private String[] getLines(String fileContent) throws CSVException {
		logger.info("filecontent [ " + fileContent + " ]");
		while (fileContent.indexOf("\r\n") != -1) {
			int index = fileContent.indexOf("\r\n");
			fileContent = fileContent.substring(0, index).concat("@")
					.concat(fileContent.substring(index + 2, fileContent.length()));
		}
		logger.info("filecontent [ " + fileContent + " ]");

		while (fileContent.indexOf("\\n") != -1) {
			int index = fileContent.indexOf("\\n");
			fileContent = fileContent.substring(0, index).concat("@")
					.concat(fileContent.substring(index + 2, fileContent.length()));
		}
		fileContent = fileContent.replaceAll("\"", "").trim();
		String[] lines = new String[0];
		if(fileContent.indexOf("@")!=-1) lines = fileContent.split("@");
		return lines;
	}

	private String formatXMLContent(String fileContent) {
		fileContent = fileContent.replaceAll("    ", "");
		fileContent = fileContent.substring(1);
		fileContent = fileContent.substring(0, fileContent.length() - 1);
		while (fileContent.indexOf("\\n") != -1) {
			int index = fileContent.indexOf("\\n");
			fileContent = fileContent.substring(0, index)
					.concat(fileContent.substring(index + 2, fileContent.length()));
		}
		while (fileContent.indexOf("\\") != -1) {
			int index = fileContent.indexOf("\\");
			fileContent = fileContent.substring(0, index)
					.concat(fileContent.substring(index + 1, fileContent.length()));
		}
		return fileContent;
	}

	private List<CustomerStatement> findDuplicateReferenceNumberList(List<CustomerStatement> originalList) {
		// Group records by reference number
		Map<Integer, List<CustomerStatement>> recordsByReferece = originalList.stream()
				.collect(Collectors.groupingBy(CustomerStatement::getTransactionReference));

		List<CustomerStatement> duplicateList = new ArrayList<CustomerStatement>();
		recordsByReferece.forEach((reference, personWithTheSameRef) -> {
			if (personWithTheSameRef.size() > 1) {
				List<CustomerStatement> referenceList = originalList.stream()
						.filter(c -> c.getTransactionReference().equals(reference)).collect(Collectors.toList());
				duplicateList.addAll(referenceList);
			}
		});
		return duplicateList;
	}

	private List<CustomerStatement> findInvalidEndBalaceList(List<CustomerStatement> originalList) {
		originalList.forEach(o -> logger
				.info("sum of first two [" + (o.getStartBalance().doubleValue() + o.getMutation().doubleValue())
						+ "] And endBalance [" + o.getEndBalance() + "]"));
		return originalList.stream().filter(
				c -> AssignmentsUtil.Round(c.getStartBalance().doubleValue() + c.getMutation().doubleValue()) != c
						.getEndBalance().doubleValue())
				.collect(Collectors.toList());
	}

	private CustomerStatement[] validateCustomerStatements(List<CustomerStatement> originalList) {
		List<CustomerStatement> resultList = new ArrayList<CustomerStatement>();
		List<CustomerStatement> duplicateReferenceNumberList = findDuplicateReferenceNumberList(originalList);
		List<CustomerStatement> invalidEndBalanceList = findInvalidEndBalaceList(originalList);
		duplicateReferenceNumberList.forEach(d -> d.setFailedReason("Duplicate Reference Number"));
		invalidEndBalanceList.forEach(i -> i.setFailedReason("Invalid End Balance"));
		resultList.addAll(duplicateReferenceNumberList);
		resultList.addAll(invalidEndBalanceList);
		Object[] customerStatements = resultList.toArray();
		CustomerStatement[] invalidStatements = new CustomerStatement[customerStatements.length];
		System.arraycopy(customerStatements, 0, invalidStatements, 0, customerStatements.length);
		return invalidStatements;
	}
}
