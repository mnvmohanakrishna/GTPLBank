package com.gtpl.dataProviders;

import java.io.IOException;
import org.testng.annotations.DataProvider;
import com.gtpl.base.BaseClass;
import com.gtpl.utilities.ExcelUtilities;

public class DataProviders extends BaseClass {

	// Read single cell value in a column from excel
	@DataProvider(name = "SingleCellValue")
	public String[] getSingleCellValue() throws IOException {
		int rownum = ExcelUtilities.getRowCount(excelPath, "ExcelSheetName"); // Get row count
		String[] SingleCellValue = new String[rownum]; // Create string array with size of row count
		for (int i = 0; i < rownum; i++) {
			SingleCellValue[i] = ExcelUtilities.getCellData(excelPath, "Credentials", i + 1, 0);
		}
		return SingleCellValue;
	}

	// Read multiple cell values in a column from excel
	@DataProvider(name = "MultipleCellValuesInColumn")
	public String[] getMultipleCellValuesInColumn() throws IOException {
		int rownum = ExcelUtilities.getRowCount(excelPath, "ExcelSheetName"); // Get row count
		String[] listMultipleCellValuesInColumn = new String[rownum]; // Create String array with size of row count
		for (int i = 0; i < rownum; i++) {
			listMultipleCellValuesInColumn[i] = ExcelUtilities.getCellData(excelPath, "MultipleSingleProperties", i + 1,
					0);
		}
		return listMultipleCellValuesInColumn;
	}

	// Read cells values in a row from excel
	@DataProvider(name = "MultipleCellValuesInRow")
	public String[][] getMultipleCellValuesInRow() throws IOException {
		int rows = ExcelUtilities.getRowCount(excelPath, "ExcelSheetName"); // Totals rows count
		int columns = ExcelUtilities.getCellCount(excelPath, "ExcelSheetName", rows); // Total Columns

		String[][] MultipleCellValuesInRow = new String[rows][columns];

		for (int i = 0; i < rows; i++) { // Iterate rows
			for (int j = 0; j < columns; j++) { // Iterate columns
				MultipleCellValuesInRow[i][j] = ExcelUtilities.getCellData(excelPath, "DualProperty", i + 1, j);
			}
		}
		return MultipleCellValuesInRow;
	}

	// Read mutiple cell values in the all rows from excel
	@DataProvider(name = "MultipleCellValuesInAllRows")
	public String[][] getMultipleCellValuesInAllRows() throws IOException {
		int rows = ExcelUtilities.getRowCount(excelPath, "ExcelSheetName"); // Totals rows count
		int columns = ExcelUtilities.getCellCount(excelPath, "ExcelSheetName", rows); // Total Columns

		String[][] listMultipleCellValuesInAllRows = new String[rows][columns];

		for (int i = 0; i < rows; i++) // Iterate rows
		{
			for (int j = 0; j < columns; j++) // Iterate columns
			{
				listMultipleCellValuesInAllRows[i][j] = ExcelUtilities.getCellData(excelPath, "ExcelSheetName", i + 1,
						j); // Get cell data includes columns iterate row wise
			}
		}
		return listMultipleCellValuesInAllRows;
	}

	// Read all the cell data from excel and store in a list
	@DataProvider(name = "AllCellValues")
	public Object[][] dataProviderfunc() {
		Object[][] data = null;
		try {
			data = ExcelUtilities.storeExcelDataInToList(excelPath, "ExcelSheetName");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
