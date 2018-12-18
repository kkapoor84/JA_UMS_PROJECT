package com.ja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class Excel_To_Map {

	@SuppressWarnings("deprecation")
	public HashMap<String, String> readExcelSheet(final String workBookName, final String sheetName) {

		System.out.println(
				"Opening the excel file to read data from: " + workBookName + " workbook, " + sheetName + " worksheet");

		HashMap<String, String> dataMap = new HashMap<String, String>();

		try {

			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\" + workBookName)));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			String mapKey = new String();
			String mapValue = new String();
			int rowCounter = 0;

			System.out.println("reading the data from the excel data sheet");

			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {

				HSSFRow row = (HSSFRow) rows.next();
				rowCounter = 0;
				String cellText = null;

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {

					HSSFCell cell = (HSSFCell) cells.next();

					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

						cellText = cell.getRichStringCellValue().getString();

					} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

						cellText = String.valueOf((int) cell.getNumericCellValue());

					} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

						cellText = String.valueOf(cell.getBooleanCellValue());

					} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
						cellText = evaluator.evaluate(cell).getStringValue();

						SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
						if (cell.getDateCellValue() != null) {
							cellText = sdf.format(cell.getDateCellValue());
						} else {

						}

					} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {

						cellText = null;
						continue;
					}

					else {
						System.out.println("Not a valid type");
					}

					if (rowCounter == 0) {

						mapKey = cellText;
						rowCounter = rowCounter + 1;

					} else if (rowCounter == 1) {

						mapValue = cellText;
						rowCounter = rowCounter + 1;
					}

					dataMap.put(mapKey, mapValue);
				}
			}

			workbook.close();
			workbook = null;
			sheet = null;

			System.out.println("Closing the excel file and returning to the test...");

		} catch (IOException e) {

			System.out.println("Unable to read from the excel file -  " + workBookName);

			e.printStackTrace();

		} finally {

			System.out.println("Inside the finally Method of the readExcelSheet method");

		}

		return dataMap;

	}

	/**
	 * 
	 * @param sheetName-sheet
	 *            name present in GMED.xls
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> readExcel(String sheetName) throws Exception {
		Property_File_Reader PFR = new Property_File_Reader();
		Excel_To_Map testDataObj = new Excel_To_Map();
		return testDataObj.readExcelSheet(PFR.getvalue("FileNameColumnHeader"), "JAClassTableData");
	}

}
