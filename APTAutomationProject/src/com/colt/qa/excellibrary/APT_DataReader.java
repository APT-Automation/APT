package com.colt.qa.excellibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class APT_DataReader {

	public static void main(String[] args) throws IOException {

//		dataReaderUsinghashTable();
		//FinalDataReader(); // For Checking data
		
		//FinalDataReader_PK();
		DataProviderMethod_SI();

	}

	/**
	 * Reading data using Hastable approach
	 * 
	 * @return
	 * @throws IOException
	 */

	
	@DataProvider
    public static Object[][] DataReader_createService() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("Lanlink");

		int rowCount = sheet.getLastRowNum();
		 
		int colCount = sheet.getRow(0).getLastCellNum();
	 

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





    
    @DataProvider
    public static Object[][] FinalDataReader_CreateCustomer() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CreateCustomer");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    
    @DataProvider
    public static Object[][] FinalDataReader_EditCustomer() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));
		

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("EditCustomer");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    @DataProvider
    public static Object[][] DataReader_CustomerToBeEdited() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CustomerToBeEdited");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    
    @DataProvider
    public static Object[][] FinalDataReader_CreateCustomer_AddUser() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("AddUser");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    
    @DataProvider
    public static Object[][] DataReader_UserToBeEdited() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("UserToBeEdited");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    
    
    
    
    
    
    
    
    
    @DataProvider
    public static Object[][] DataReader_SearchCustomer() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}
		Sheet sheet = workbook.getSheet("SearchCustomer");			 //Sheet sheet = workbook.getSheetAt(2);

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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

    
    
    
    
    
    
    
    
    
    
    
    
    
    @DataProvider
    public static Object[][] FinalDataReader2_PK() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(1);

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    
    
    @DataProvider
    public static Object[][] DataReader_CreateOrderService() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheet("CreateOrderService");			//Sheet sheet = workbook.getSheetAt(3);
		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    @DataProvider
    public static Object[][] FinalDataReader5_PK() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(4);

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    @DataProvider
    public static Object[][] FinalDataReader6_PK() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(5);

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    
    
    
    @DataProvider
    public static Object[][] FinalDataReader9_PK() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(8);

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
    @DataProvider
    public static Object[][] FinalDataReader_EditCustomer1() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CustomerToBeEdited");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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


/**
* data reader method 2
*/

  @DataProvider
    public static Object[][] FinalDataReader11_PK() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";
		
		File file = new File(filename1);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String fileExtensionName = filename1.substring(filename1.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		}
		else if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}

		Sheet sheet = workbook.getSheet("CustomerToBeEdited");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
  
  
  
  @DataProvider
  public static Object[][] FinalDataReader12_PK() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("UserToBeEdited");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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


/**
* data reader method 2
*/

@DataProvider
  public static Object[][] FinalDataReader13_PK() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";
		
		File file = new File(filename1);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String fileExtensionName = filename1.substring(filename1.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		}
		else if (fileExtensionName.equals(".xls")) {
			workbook = new HSSFWorkbook(inputStream);
		}

		Sheet sheet = workbook.getSheet("UserToBeEdited");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
  


  
	@DataProvider
	public static Object[][] DataProviderMethod_SI() throws IOException {

		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx" ;

		File file = new File(filename);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(0);
		
		int rowCount = sheet.getLastRowNum();

		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[rowCount][1];

		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();

			for (int j = 0; j < colCount; j++) {

				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());

			}

			obj[i][0] = datamap;

		}
		
		System.out.println("The value found for first row is: "+ obj[0][0]);

		return obj;
	}
    
    
	@DataProvider
    public static Object[][] FinalDataReader_negative() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(10);

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
	
	
	@DataProvider
    public static Object[][] FinalDataReader_DeleteCustomer() throws IOException {

		String filename1 = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";

		File file = new File(filename1);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename1.substring(filename1.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("DeleteCustomer");

		int rowCount = sheet.getLastRowNum();
		System.out.println("Row count: "+rowCount);
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
    
	
	
	@DataProvider(name = "SampleOrder1", parallel = false)
	public static Object[][] dataReaderUsinghashTable() throws IOException {

		System.out.println("Fetching the file");
		FileInputStream file = new FileInputStream(
				new File("C:\\Users\\sivas.k\\eclipse-workspace\\APTLearning\\src\\Data\\sampleDate.xlsx"));
		System.out.println("Entered inside the file");

		XSSFWorkbook workbook = new XSSFWorkbook(file);

		XSSFSheet sheet = workbook.getSheetAt(0);
		int row_count = sheet.getLastRowNum(); // It returns in index values
		int TotalRowCount = row_count + 1;
		int totalrowrequired = 0;
		int Total_column = 0;

		System.out.println("Total row count is " + TotalRowCount);

		for (int i = 0; i < TotalRowCount; i++) {

			XSSFRow row = sheet.getRow(i);

			if (!(sheet.getRow(i)).equals(null)) { // We can enter only if there is any value in that row

				totalrowrequired = totalrowrequired + 1;

			}

			Total_column = row.getLastCellNum();
			System.out.println("No.of. column for " + i + "st row is :" + Total_column);

		}

		Hashtable<String, String> table = new Hashtable<String, String>();

		Object[][] listOfLists = new Object[TotalRowCount - 1][1];

		for (int j = 1; j < TotalRowCount; j++) {// j for Row

			int y = (j - 1);
			for (int k = 0; k < Total_column; k++) { // k for column

				if (y > 0) {
					System.out.println("------------ " + y + "    " + "-------" + listOfLists[0][0]);
				}

				XSSFRow Keyrow = sheet.getRow(0);
				XSSFRow rowvalue = sheet.getRow(j);

				table.put(Keyrow.getCell(k).toString(), rowvalue.getCell(k).toString());

			}

			listOfLists[j - 1][0] = table;
		}

		System.out.println("The table size is : " + table.size());

		for (String hh : table.keySet()) {

			System.out.println("---------");

			System.out.println("Inside the table: " + table.get("First"));

		}

		System.out.println("the length of the 2D array is: " + listOfLists.length);

		for (int i = 0; i < listOfLists.length; i++) {

			System.out.println("NUmber of runs: " + i);
			System.out.println("The values passed are: " + listOfLists[i][0]);
		}

		return null;

	}

	@DataProvider(name = "SampleOrder2", parallel = false)
	public static Object[][] datareader() throws IOException {

		String filename = "sampleDate.xlsx";

		File file = new File("C:\\Users\\sivas.k\\eclipse-workspace\\APTLearning\\src\\Data\\sampleDate.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(0);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		Hashtable<String, String> table = new Hashtable<String, String>();

		Object[][] listOfLists = new Object[rowCount][1];

		List<Hashtable> twoDdata = new ArrayList<Hashtable>();

		Object obj = null;
		for (int i = 1; i < rowCount + 1; i++) {
			ArrayList tabData = new ArrayList();
			Row row = sheet.getRow(i);

			Object[] tabDataRows = null;
			for (int j = 0; j < row.getLastCellNum(); j++) {

//	        	Row Keyrow = sheet.getRow(0);
//				table.put(sheet.getRow(0).getCell(j).toString(), row.getCell(j).toString()); // table.put( Enter the
				table.put(sheet.getRow(0).getCell(j).toString(), row.getCell(j).toString()); // table.put( Enter the
				// first row details as
				//System.out.println("2D : " + table);
				// a key, Iterate the

				// remaining row as a
				
				
				

			}
			
			Object[] keys = table.keySet().toArray();
			Object[] values = table.values().toArray();
			
			twoDdata.add(table);
//			obj = table; 
//			System.out.println("i"+i);


			
			//listOfLists[i - 1][0] = tabData;

			//System.out.println("Before clear " + listOfLists[i - 1][0]);
//			tabData.add(i, obj);
			System.out.println("tabData"+table);
			
//			System.out.println("tabData"+tabData);

//			table.clear();

			//System.out.println("After clear " + listOfLists[i - 1][0]);
			//twoDdata.add(table);
		}
		
		 String[] arr1 = new String[table.size()];
		    String[] arr2 = new String[table.size()];
		    Set entries = table.entrySet();
		    Iterator entriesIterator = entries.iterator();
		    
		    int i = 0;
		    while(entriesIterator.hasNext()){

		        Map.Entry mapping = (Map.Entry) entriesIterator.next();

		        arr1[i] = mapping.getKey().toString();
		        arr2[i] = mapping.getValue().toString();
		        
		        System.out.println(mapping.getKey().toString());

		        i++;
		    }

		

		
		// twoDdata.add(table);

		 System.out.println("twoDdata==========in"+twoDdata);
//		table.clear();

		// System.out.println("twoDdata-=======Out"+twoDdata);

		for (int z = 0; z < listOfLists.length; z++) {

			System.out.println("NUmber of runs: " + i);
			System.out.println("The values passed are: " + listOfLists[i][0]);
		}

		return listOfLists;
	}
	
	
	
	/**
	 * By Pramod
	 */
	public static XSSFWorkbook workbook;
    public static XSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    //public static String file_location = System.getProperty("user.dir")+"OrangeHRM3";
    public static String file_location ="C:\\Users\\pramod.k\\eclipse-workspace\\APT_Automation3\\src\\com\\colt\\qa\\datalibrary\\CreateCustomer.xlsx";
    static String SheetName= "DataSheet";
    public  String Res;
    //Write obj1=new Write();
    public int DataSet=-1;
    
    @DataProvider
    public static Object[][] getDataFromExcel() throws IOException {
    FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
        worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
        XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
     
        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        //int RowNum = worksheet.getLastRowNum();  // Now working as expected
        System.out.println("No. Of Rows:  "+RowNum);
        int ColNum= Row.getLastCellNum(); // get last ColNum 
        System.out.println("No. Of Columns:  "+ColNum);
         
        Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
         
            for(int i=0; i<RowNum-1; i++) //Loop work for Rows
            {  
            	XSSFRow row= worksheet.getRow(i+1);
                 
                for (int j=0; j<ColNum; j++) //Loop work for colNum
                {
                    if(row==null)
                        Data[i][j]= "";
                    else
                    {
                    	XSSFCell cell= row.getCell(j);
                        if(cell==null)
                            Data[i][j]= ""; //if it get Null value it pass no data 
                        else
                        {
                            String value=formatter.formatCellValue(cell);
                            Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                            
                            System.out.println("Value for all data: "+Data[i][j]);
                        }
                    }
                }
            }
        return Data;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
