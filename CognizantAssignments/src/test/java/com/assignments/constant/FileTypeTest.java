package com.assignments.constant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author Jagadish Anala
 *
 */
public class FileTypeTest {

	@Test
	public void csv() {
		assertThat(FileType.csv, is(notNullValue()));
	}

	@Test
	public void xml() {
		assertThat(FileType.xml, is(notNullValue()));
	}
}
