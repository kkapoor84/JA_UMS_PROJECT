package com.ja.utils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class Excel_To_DataProvider {

	public static String excelfilepath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\Credentials.xlsx";

	public static Object[][] GetData(String sheetname) throws Exception {
		File F = new File(excelfilepath);
		FileInputStream FIS = new FileInputStream(F);
		XSSFWorkbook WB = new XSSFWorkbook(FIS);
		XSSFSheet sheet = WB.getSheet(sheetname);

		int totalrowcount = sheet.getLastRowNum();
		int totalcoulmncount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[totalrowcount][totalcoulmncount];

		for (int i = 1; i <= totalrowcount; i++) {
			for (int j = 0; j < totalcoulmncount; j++) {
				data[i - 1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}

		return data;
	}

	@DataProvider(name = "Datafactory")
	public Object[][] excelData() throws Exception {

		Object data[][] = Excel_To_DataProvider.GetData("Sheet1");
		return data;

	}

}
