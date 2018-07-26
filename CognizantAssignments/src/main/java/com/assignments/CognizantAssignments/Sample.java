package com.assignments.CognizantAssignments;

public class Sample {
public static void main(String args[]) {
	
	String fileContent = "\"First name\",\"Sur name\",\"Issue count\",\"Date of birth\"\r\n\"Theo\",\"Jansen\",5,\"1978-01-02T00:00:00\"\r\n\"Fiona\",\"de Vries\",7,\"1950-11-12T00:00:00\"\r\n\"Petra\",\"Boersma\",1,\"2001-04-20T00:00:00\"";
	String[] lines = fileContent.split("(\r\n|\r|\n)", -1);
	fileContent = fileContent.replace("\r\n", "@");

//	while (fileContent.indexOf("\\n") != -1) {
//		int index = fileContent.indexOf("\\n");
//		fileContent = fileContent.substring(0, index).concat("@")
//				.concat(fileContent.substring(index + 2, fileContent.length()));
//	}
//	System.out.println("fileContent=========>"+fileContent);
//
//	while (fileContent.indexOf("\\r") != -1) {
//		int index = fileContent.indexOf("\\r");
//		fileContent = fileContent.substring(0, index).concat("@")
//				.concat(fileContent.substring(index + 2, fileContent.length()));
//	}
//	fileContent = fileContent.replaceAll("\"", "").trim();
//	String[] lines = new String[0];
	lines = fileContent.split("@");
	//fileContent = fileContent.replaceAll("\"", "");
	System.out.println("fileContent=========>"+fileContent);
	System.out.println("lines=========>"+lines.length);

}
}
