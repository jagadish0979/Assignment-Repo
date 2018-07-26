package com.assignments.util;

import java.text.DecimalFormat;

/**
 * @author Jagadish Anala
 *
 */
public class AssignmentsUtil {

	public static Double Round(Double val) {
		DecimalFormat format = new DecimalFormat("##.00");
		return Double.valueOf(format.format(val));
	}
}
