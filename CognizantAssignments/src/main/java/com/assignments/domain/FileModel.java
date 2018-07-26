package com.assignments.domain;

import com.assignments.constant.FileType;

/**
 * @author Jagadish Anala
 *
 */
public class FileModel {
	private String fileName;
	private String fileContent;
	private FileType fileType;

	/**
	 * @return the fileContent
	 */
	public String getFileContent() {
		return fileContent;
	}

	/**
	 * @param fileContent the fileContent to set
	 */
	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType.toString();
	}

	public void setFileType(String fileType) {
		this.fileType = FileType.valueOf(fileType);
	}
}
