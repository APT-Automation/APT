package com.colt.qa.excellibrary;

import java.io.File;
import java.io.FileInputStream;
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
	public static Object[][] Datareader_BasicValidation() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_TestData_BasicValidation.xlsx";

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

		Sheet sheet = workbook.getSheet("BasicValidation");

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
	
	
	@DataProvider
	public static Object[][] Datareader_CreateOrder_IPVPN_IPVPNAccess() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_IPVPN.xlsx";

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

		Sheet sheet = workbook.getSheet("IPVPN_IPVPNAccess");

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
	
	
	
	@DataProvider
	public static Object[][] Datareader_CreateOrder_IPVPN_IPVoice() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_IPVPN.xlsx";

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

		Sheet sheet = workbook.getSheet("IPVPN_IPVoice");

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
	
	
	
	@DataProvider
	public static Object[][] Datareader_CreateOrder_IPVPN_CPESolutionsL3() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_IPVPN.xlsx";

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

		Sheet sheet = workbook.getSheet("IPVPN_CPESolutionsL3");

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
	
	
	
	
	@DataProvider
	public static Object[][] Datareader_CreateOrder_IPVPN_CPESolutionsL2() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_IPVPN.xlsx";

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

		Sheet sheet = workbook.getSheet("IPVPN_CPESolutionsL2");

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

		Sheet sheet = workbook.getSheet("VOIPAccess");

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
	public static Object[][] Datareader_CreateOredr_VOIP() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_VOIP.xlsx";

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

		Sheet sheet = workbook.getSheet("VOIPAccess");

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
	
	
	
	
	
	
	
	@DataProvider
	public static Object[][] Finaldatareader_CreateOrderMandatoryFields() throws IOException {

		String filename = "APT_MCS_TESTDATA_PK.xlsx";

		File file = new File("C:\\Users\\shwetha.j\\Desktop\\APT_Eclipse_Code\\Shwetha_New_Code_Backup\\APT_Automation\\src\\com\\colt\\qa\\datalibrary");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CreateOrderMandatoryData");

		int rowCount = sheet.getLastRowNum();
		
		System.out.println("Ttotal row count: "+rowCount);

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
	public static Object[][] Finaldatareader_CreateOrderIPAccess_ONSETOFFSET() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_VOIP.xlsx";

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

		Sheet sheet = workbook.getSheet("IPACCESSONSETOFFSET");

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
	
	
	@DataProvider
	public static Object[][] Finaldatareader_CreateOrderIPAccess_ONSETOFFSET_1() throws IOException {
		
		String filename = "src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_ServiceType.xlsx";

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

		Sheet sheet = workbook.getSheet("ViewService_UsersInfo");

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@DataProvider
	public static Object[][] Finaldatareader() throws IOException {

		String filename = "APT_MCN_TESTDATA.xlsx";

		File file = new File("C:\\Users\\shwetha.j\\Desktop\\APT_Eclipse_Code\\Shwetha_New_Code_Backup\\APT_Automation\\src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_ServiceType.xlsx");

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
		
		System.out.println("Ttotal row count: "+rowCount);

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
	public static Object[][] Finaldatareader1() throws IOException {

		String filename = "APT_MCN_TESTDATA.xlsx";

		File file = new File("C:\\Users\\shwetha.j\\Desktop\\APT_QA_Softwares\\APT_EclipseCode\\APT_Automation\\src\\com\\colt\\qa\\datalibrary\\APT_MCN_TESTDATA.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(1);

		int rowCount = sheet.getLastRowNum();
		
		System.out.println("Ttotal row count: "+rowCount);

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
	public static Object[][] Finaldatareader3() throws IOException {

		String filename = "APT_MCN_TESTDATA.xlsx";

		File file = new File("C:\\Users\\shwetha.j\\Desktop\\APT_QA_Softwares\\APT_EclipseCode\\APT_Automation\\src\\com\\colt\\qa\\datalibrary\\APT_MCN_TESTDATA.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(2);

		int rowCount = sheet.getLastRowNum();
		
		System.out.println("Ttotal row count: "+rowCount);

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
	public static Object[][] Finaldatareader4() throws IOException {

		String filename = "APT_MCN_TESTDATA.xlsx";

		File file = new File("C:\\Users\\shwetha.j\\Desktop\\APT_QA_Softwares\\APT_EclipseCode\\APT_Automation\\src\\com\\colt\\qa\\datalibrary\\APT_MCN_TESTDATA.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(3);

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
	
	
	@DataProvider
	public static Object[][] Finaldatareader5() throws IOException {

		String filename = "APT_MCN_TESTDATA.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_TESTDATA.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(4);

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
	
	@DataProvider
	public static Object[][] Finaldatareader6() throws IOException {

		String filename = "APT_MCN_TESTDATA.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_TESTDATA.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheetAt(5);

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
	
	
	@DataProvider
	public static Object[][] Finaldatareader_PK() throws IOException {

		String filename = "APT_MCN_TESTDATA.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("SiteOrderTestdata");

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
