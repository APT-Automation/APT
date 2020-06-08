package com.saksoft.qa.driverlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataReader_PK {
	
	
	public static void main(String[] args) throws IOException {
		
		Finaldatareader();
	}
	
	
	
	
	@DataProvider
	public static Object[][] Finaldatareader() throws IOException {
		
		String filename = "APT_Data.xlsx";

		File file = new File("src\\com\\saksoft\\qa\\datalibrary\\APT_Data.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("Sheet1");

		int rowCount = sheet.getLastRowNum();
		
		System.out.println("total row count: "+rowCount);

		int colCount = sheet.getRow(0).getLastCellNum();

		
		System.out.println("Column count: "+colCount);
		Object[][] obj = new Object[rowCount][1];

		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();

			for (int j = 0; j < colCount; j++) {

				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());

			}

			obj[i][0] = datamap;
			

			System.out.println("The values found are " + obj[i][0]);
		}

		return obj;
	}
	
}
