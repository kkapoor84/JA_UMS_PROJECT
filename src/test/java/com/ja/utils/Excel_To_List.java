package com.ja.utils;

import java.util.ArrayList;
import java.util.List;

public class Excel_To_List {

	public List<String> GetData_From_Excel_And_STORE_IN_LIST(String sheet) throws Exception {

		Xls_Reader x = new Xls_Reader(System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\ExcelHeaderFile.xls");

		x.getRowCount(sheet);
		x.getColumnCount(sheet);

		List<String> list = new ArrayList<String>();

		for (int i = 2; i <= x.getRowCount(sheet); i++) {
			for (int j = 0; j < x.getColumnCount(sheet); j++) {
				String a = x.getCellData(sheet, j, i);
				list.add(a);
			}

		}
		return list;

	}

	public static List<String> excelToList(String sheetName) throws Exception {

		Excel_To_List ETL = new Excel_To_List();
		return ETL.GetData_From_Excel_And_STORE_IN_LIST(sheetName);

	}

}
