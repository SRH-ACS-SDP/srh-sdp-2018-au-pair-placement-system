package edu.srh.aupair.userProfileOperations;

import java.util.List;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;

public class BuildTable {
	List<String[]> allRows = new LinkedList<String[]>();

	private int[] columnWidths() {
		int column = -1;
		int[] widthOfColumns = new int[column];
		for (String[] singleRow : allRows)
			column = Math.max(column, singleRow.length);
		for (String[] singleRow : allRows) {
			for (int columnNo = 0; columnNo < singleRow.length; columnNo++) {
				int length=StringUtils.length(singleRow[columnNo]);
				widthOfColumns[columnNo] = Math.max(widthOfColumns[columnNo], length);
			}
		}
		return widthOfColumns;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		int[] columnWidths = columnWidths();

		for (String[] singleRow : allRows) {
			for (int columnNo = 0; columnNo < singleRow.length; columnNo++) {
				String str=StringUtils.defaultString(singleRow[columnNo]);
				stringBuilder.append(StringUtils.rightPad(str, columnWidths[columnNo]));
				stringBuilder.append(' ');
			}
			stringBuilder.append('\n');
		}

		return stringBuilder.toString();
	}

	public void addRow(String... columns) {
		allRows.add(columns);
	}
}
