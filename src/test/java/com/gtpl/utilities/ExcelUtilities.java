package com.gtpl.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;

	// Reads an excel file and returns the number of rows in a specified sheet
	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	// Returns the number of cells in a specified row of a specified sheet.
	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	// Read and retrieve the value of a specific cell from an excel sheet.
	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	// Writes a given string (data) to a specified cell in an excel file.
	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	// Reads data from an excel file and stores it into a 2D array
	public static Object[][] storeExcelDataInToList(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);

		int totalRow = ws.getLastRowNum() + 1;
		int maxcol = ws.getRow(0).getLastCellNum();
		int temp = 0;
		for (int i = 0; i < totalRow; i++) {
			for (int j = 0; j < ws.getRow(i).getLastCellNum(); j++) {
				temp = ws.getRow(i).getLastCellNum();
				if (temp > maxcol) {
					maxcol = temp;
				}
			}
		}

		Object[][] tableData = new String[totalRow][maxcol];
		for (int i = 0; i < totalRow; i++) {
			for (int j = 0; j < ws.getRow(i).getLastCellNum(); j++) {
				tableData[i][j] = ws.getRow(i).getCell(j).toString();
			}
		}

		wb.close();
		fi.close();
		return tableData;
	}
}
