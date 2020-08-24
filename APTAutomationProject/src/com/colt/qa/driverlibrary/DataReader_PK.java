package com.colt.qa.driverlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataReader_PK {
	
	
	public static void main(String[] args) throws IOException {
		
		Finaldatareader();
	}
	
	
	
	/**
	 * For Create Access Core Device_ Core Router
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_CoreRouter_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CoreRouter");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();

		int colCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	/**
	 * For Create Access Core Device_ Voice Access DAS Gateway
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_VOIPAccessDASSwitch_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("VOIPAccessDASSwitch");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();

		int colCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Voice Gateway
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_VoiceGateway_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("VoiceGateway");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();

		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Load Balancer
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_LoadBalancer_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("LoadBalancer");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Traffic Aggregator
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_TrafficAggregator_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("TrafficAggregator");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		

		int colCount = sheet.getRow(0).getLastCellNum();

		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
		}

		return obj;
	}

	
	
	
	@DataProvider
	public static Object[][] DataReader_RouterCPE_Device() throws IOException {
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("RouterCPE");

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
	
	
	/**
	 * For Create Access Core Device_ DSLAM
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_DSLAM_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("DSLAM");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		

		int colCount = sheet.getRow(0).getLastCellNum();

		
		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	/**
	 * For Create Access Core Device_ Mini DSLAM
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_MiniDSLAM_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("MiniDSLAM");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Prizmnet
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_Prizmnet_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("Prizmnet");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ MDF Firewall
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_MDFFirewall_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("MDFFirewall");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		int colCount = sheet.getRow(0).getLastCellNum();

		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
		}

		return obj;
	}

	
		
	
	/**
	 * For Create Access Core Device _ Key Server device
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_Keyserver_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("Keyserver");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		

		int colCount = sheet.getRow(0).getLastCellNum();

		
		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}
	
	
	
	/**
	 * For Create Access Core Device _ DCN device
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_DCNDevice_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("DCNDevice");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		

		int colCount = sheet.getRow(0).getLastCellNum();

		
		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}
	
	
	
//	@DataProvider
//	public static Object[][] DataReader_CoreRouter_Device() throws IOException {
//		
//		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";
//
//		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
//		
//		FileInputStream inputStream = new FileInputStream(file);
//
//		Workbook workbook = null;
//
//		String fileExtensionName = filename.substring(filename.indexOf("."));
//
//		if (fileExtensionName.equals(".xlsx")) {
//
//			workbook = new XSSFWorkbook(inputStream);
//
//		}
//
//		else if (fileExtensionName.equals(".xls")) {
//
//			workbook = new HSSFWorkbook(inputStream);
//
//		}
//
//		Sheet sheet = workbook.getSheet("CoreRouter");
//
//		int rowCount = sheet.getLastRowNum();
//		
//		
//
//		int colCount = sheet.getRow(0).getLastCellNum();
//
//		
//		
//		Object[][] obj = new Object[rowCount][1];
//
//		for (int i = 0; i < rowCount; i++) {
//
//			Map<Object, Object> datamap = new HashMap<Object, Object>();
//
//			for (int j = 0; j < colCount; j++) {
//
//				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
//
//			}
//
//			obj[i][0] = datamap;
//			
//
//			System.out.println("The values found are " + obj[i][0]);
//		}
//
//		return obj;
//	}
	
	
	
	@DataProvider
	public static Object[][] DataReader_AccessSwitch_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("AccessSwitch");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		

		int colCount = sheet.getRow(0).getLastCellNum();

		
		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}
	

		
	/*
	 * For Create Access Core Device _ Access Router
	 */
		@DataProvider
		public static Object[][] DataReader_AccessRouter_Device() throws IOException {
			
			int count=0;
			
			String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

			File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
			
			FileInputStream inputStream = new FileInputStream(file);

			Workbook workbook = null;

			String fileExtensionName = filename.substring(filename.indexOf("."));

			if (fileExtensionName.equals(".xlsx")) {

				workbook = new XSSFWorkbook(inputStream);

			}

			else if (fileExtensionName.equals(".xls")) {

				workbook = new HSSFWorkbook(inputStream);

			}

			Sheet sheet = workbook.getSheet("AccessRouter");
			
			int rowCountForMap = 0;

	        for(int k=1;k<=sheet.getLastRowNum();k++){
	                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
	                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
	                        {
	                        	rowCountForMap=rowCountForMap+1;
	                        }
	        }


			int rowCount = sheet.getLastRowNum();
			
			

			int colCount = sheet.getRow(0).getLastCellNum();

			
			
			Object[][] obj = new Object[rowCountForMap][1];

			
			for (int i = 0; i < rowCount; i++) {

				Map<Object, Object> datamap = new HashMap<Object, Object>();
				
				if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
					
					for (int j = 0; j < colCount; j++) {

						datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
					
					}
					
					obj[count][0] = datamap;
					count++;
				}
				else {
					System.out.println("No changes");
				}
				
			}

			return obj;
		}

	
	
	
	
	@DataProvider
	public static Object[][] Read_CreateDeviceTestdata() throws IOException {
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CreateDevice");

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
	public static Object[][] Read_EditDeviceTestdata() throws IOException {
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("EditDevice");

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
	public static Object[][] Finaldatareader_CreateOrder_NGIN_Customer() throws IOException {
		
		String filename = "APT_CreateOrder_NGIN.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_NGIN.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CustomerData");

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
	public static Object[][] Finaldatareader_CreateOrder_NGIN() throws IOException {
		
		String filename = "APT_CreateOrder_NGIN.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_NGIN.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("NGIN");

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
	public static Object[][] Finaldatareader_CreateOrder_WholesaleSIPTrunk_Customer() throws IOException {
		
		String filename = "APT_CreateOrder_ServiceType.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_WholesaleSIPTrunk.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("CustomerData");

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
	public static Object[][] Finaldatareader_CreateOrder_WholesaleSIPTrunk() throws IOException {
		
		String filename = "APT_CreateOrder_ServiceType.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_WholesaleSIPTrunk.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("WholesaleSIPTrunk");

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
		
		String filename = "APT_CreateOrder_ServiceType.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_ServiceType.xlsx");
		
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
	public static Object[][] Finaldatareader_CreateOrderIPAccess_ONSETOFFSET_Users() throws IOException {
		
		String filename = "APT_CreateOrder_ServiceType.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_CreateOrder_ServiceType.xlsx");
		
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
	
	
	/**
	 * For Create Access Core Device_ DSLAM
	 * @return
	 * @throws IOException
	 */
	@DataProvider
	public static Object[][] DataReader_Firewall_Device() throws IOException {
		
		int count=0;
		
		String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

		File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_CreateAccessCoreDevice_TestData.xlsx");
		
		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = null;

		String fileExtensionName = filename.substring(filename.indexOf("."));

		if (fileExtensionName.equals(".xlsx")) {

			workbook = new XSSFWorkbook(inputStream);

		}

		else if (fileExtensionName.equals(".xls")) {

			workbook = new HSSFWorkbook(inputStream);

		}

		Sheet sheet = workbook.getSheet("Firewall");
		
		int rowCountForMap = 0;

        for(int k=1;k<=sheet.getLastRowNum();k++){
                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
                        {
                        	rowCountForMap=rowCountForMap+1;
                        }
        }


		int rowCount = sheet.getLastRowNum();
		
		int colCount = sheet.getRow(0).getLastCellNum();

		
		Object[][] obj = new Object[rowCountForMap][1];

		
		for (int i = 0; i < rowCount; i++) {

			Map<Object, Object> datamap = new HashMap<Object, Object>();
			
			if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
				
				for (int j = 0; j < colCount; j++) {

					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				
				}
				
				obj[count][0] = datamap;
				count++;
			}
			else {
				System.out.println("No changes");
			}
			
		}

		return obj;
	}


	
	//Search Device
		@DataProvider
		public static Object[][] DataReader_SearchDevice() throws IOException {
			
			String filename = "APT_MCN_CreateAccessCoreDevice_TestData.xlsx";

			File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCN_searchDevice.xlsx");
			
			FileInputStream inputStream = new FileInputStream(file);

			Workbook workbook = null;

			String fileExtensionName = filename.substring(filename.indexOf("."));

			if (fileExtensionName.equals(".xlsx")) {

				workbook = new XSSFWorkbook(inputStream);

			}

			else if (fileExtensionName.equals(".xls")) {

				workbook = new HSSFWorkbook(inputStream);

			}

			Sheet sheet = workbook.getSheet("searchdevice");

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
		
		
		
		/**
		 * Manage Postcode data Reader
		 * @return
		 * @throws IOException
		 */
		 @DataProvider
			public static Object[][] DataReader_ManagePostcode() throws IOException {
				
				int count=0;
				
				String filename = "APT_MCS_ManagePostcode.xlsx";

				String file = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_ManagePostcode.xlsx";
				
				FileInputStream inputStream = new FileInputStream(file);

				Workbook workbook = null;

				String fileExtensionName = filename.substring(filename.indexOf("."));

				if (fileExtensionName.equals(".xlsx")) {

					workbook = new XSSFWorkbook(inputStream);

				}

				else if (fileExtensionName.equals(".xls")) {

					workbook = new HSSFWorkbook(inputStream);

				}

				Sheet sheet = workbook.getSheet("PostCode");
				
				int rowCountForMap = 0;

		        for(int k=1;k<=sheet.getLastRowNum();k++){
		                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
		                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
		                        {
		                        	rowCountForMap=rowCountForMap+1;
		                        }
		        }

				int rowCount = sheet.getLastRowNum();
				
				int colCount = sheet.getRow(0).getLastCellNum();

				Object[][] obj = new Object[rowCountForMap][1];

				
				for (int i = 0; i < rowCount; i++) {

					Map<Object, Object> datamap = new HashMap<Object, Object>();
					
					if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
						
						for (int j = 0; j < colCount; j++) {

							datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
						
						}
						
						System.out.println("basic counts "+count);
						obj[count][0] = datamap;
						count++;
					}
					else {
						System.out.println("No changes");
					}
					
				}

				return obj;
			}
		 
		 
		 /**
		  * Manage Number Translation data reader
		  * @return
		  * @throws IOException
		  */
		 @DataProvider
		    public static Object[][] DataReader_ManageTranslation() throws IOException {

				
				int count=0;
				
				String filename = "APT_IMNST_TESTDATA_VS.xlsx";

				String file = "src\\com\\colt\\qa\\datalibrary\\APT_IMNST_TESTDATA_VS.xlsx";
				
				FileInputStream inputStream = new FileInputStream(file);

				Workbook workbook = null;

				String fileExtensionName = filename.substring(filename.indexOf("."));

				if (fileExtensionName.equals(".xlsx")) {

					workbook = new XSSFWorkbook(inputStream);

				}

				else if (fileExtensionName.equals(".xls")) {

					workbook = new HSSFWorkbook(inputStream);

				}

				Sheet sheet = workbook.getSheet("IMSNT");
				
				int rowCountForMap = 0;

		        for(int k=1;k<=sheet.getLastRowNum();k++){
		                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
		                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
		                        {
		                        	rowCountForMap=rowCountForMap+1;
		                        }
		        }


				int rowCount = sheet.getLastRowNum();
				
				

				int colCount = sheet.getRow(0).getLastCellNum();

				
				
				Object[][] obj = new Object[rowCountForMap][1];

				
				for (int i = 0; i < rowCount; i++) {

					Map<Object, Object> datamap = new HashMap<Object, Object>();
					
					if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
						
						for (int j = 0; j < colCount; j++) {

							datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
						
						}
						
						System.out.println("basic counts "+count);
						obj[count][0] = datamap;
						count++;
					}
					else {
						System.out.println("No changes");
					}
					
					
					
				System.out.println("Number of count are:  "+ count);
					System.out.println("Number of count in object are:  " + obj.length);
				}

				return obj;
			}
		 
		 
		 /**
		  * Wholesale SIP Trunking Data Reader
		  * @return
		  * @throws IOException
		  */
			@DataProvider
			public static Object[][] Finaldatareader_wholeSale() throws IOException {
				
				int count=0;
				
				String filename = "APT_wholeSaleData.xlsx";

				File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_wholeSaleData.xlsx");
				
				FileInputStream inputStream = new FileInputStream(file);

				Workbook workbook = null;

				String fileExtensionName = filename.substring(filename.indexOf("."));

				if (fileExtensionName.equals(".xlsx")) {

					workbook = new XSSFWorkbook(inputStream);

				}

				else if (fileExtensionName.equals(".xls")) {

					workbook = new HSSFWorkbook(inputStream);

				}

				Sheet sheet = workbook.getSheet("wholeSaleSIPTruncking");
				
				int rowCountForMap = 0;

		        for(int k=1;k<=sheet.getLastRowNum();k++){
		                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
		                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
		                        {
		                        	rowCountForMap=rowCountForMap+1;
		                        }
		        }


				int rowCount = sheet.getLastRowNum();
				
				

				int colCount = sheet.getRow(0).getLastCellNum();

				
				
				Object[][] obj = new Object[rowCountForMap][1];

				
				for (int i = 0; i < rowCount; i++) {

					Map<Object, Object> datamap = new HashMap<Object, Object>();
					
					if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
						
						for (int j = 0; j < colCount; j++) {

							datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
						
						}
						
						System.out.println("basic counts "+count);
						obj[count][0] = datamap;
						count++;
					}
					else {
						System.out.println("No changes");
					}
					
				}

				return obj;
			}
			
			
			/**
			 * For Customer User
			 * @return
			 * @throws IOException
			 */
			@DataProvider
			public static Object[][] DataReader_CustomerFunctionality() throws IOException {
				
				int count=0;
				
				String filename = "APT_MCS_CustomerUser.xlsx";

				File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCS_CustomerUser.xlsx");
				
				FileInputStream inputStream = new FileInputStream(file);

				Workbook workbook = null;

				String fileExtensionName = filename.substring(filename.indexOf("."));

				if (fileExtensionName.equals(".xlsx")) {

					workbook = new XSSFWorkbook(inputStream);

				}

				else if (fileExtensionName.equals(".xls")) {

					workbook = new HSSFWorkbook(inputStream);

				}

				Sheet sheet = workbook.getSheet("customerUser");
				
				int rowCountForMap = 0;

		        for(int k=1;k<=sheet.getLastRowNum();k++){
		                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
		                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
		                        {
		                        	rowCountForMap=rowCountForMap+1;
		                        }
		        }


				int rowCount = sheet.getLastRowNum();
				
				int colCount = sheet.getRow(0).getLastCellNum();

				Object[][] obj = new Object[rowCountForMap][1];

				
				for (int i = 0; i < rowCount; i++) {

					Map<Object, Object> datamap = new HashMap<Object, Object>();
					
					if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
						
						for (int j = 0; j < colCount; j++) {

							datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
						
						}
						
						obj[count][0] = datamap;
						count++;
					}
					else {
						System.out.println("No changes");
					}
				}
				return obj;
			}

			
			
			/**
			 * For Customer User _ Perform Supply Operation
			 * @return
			 * @throws IOException
			 */
			@DataProvider
			public static Object[][] DataReader_CreateCustomer_AddUser_SupplyService() throws IOException {
				
				int count=0;
				
				String filename = "APT_MCS_CustomerUser.xlsx";

				File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCS_CustomerUser.xlsx");
				
				FileInputStream inputStream = new FileInputStream(file);

				Workbook workbook = null;

				String fileExtensionName = filename.substring(filename.indexOf("."));

				if (fileExtensionName.equals(".xlsx")) {

					workbook = new XSSFWorkbook(inputStream);

				}

				else if (fileExtensionName.equals(".xls")) {

					workbook = new HSSFWorkbook(inputStream);

				}

				Sheet sheet = workbook.getSheet("SupplyService");
				
				int rowCountForMap = 0;

		        for(int k=1;k<=sheet.getLastRowNum();k++){
		                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
		                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
		                        {
		                        	rowCountForMap=rowCountForMap+1;
		                        }
		        }


				int rowCount = sheet.getLastRowNum();
				
				int colCount = sheet.getRow(0).getLastCellNum();

				Object[][] obj = new Object[rowCountForMap][1];

				
				for (int i = 0; i < rowCount; i++) {

					Map<Object, Object> datamap = new HashMap<Object, Object>();
					
					if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
						
						for (int j = 0; j < colCount; j++) {

							datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
						
						}
						
						obj[count][0] = datamap;
						count++;
					}
					else {
						System.out.println("No changes");
					}
				}
				return obj;
			}
			
			
/**
 * For NGIN
 * @return
 * @throws IOException
 */
			 @DataProvider
				public static Object[][] Finaldatareader_NGIN() throws IOException {
					
					int count=0;
					
					String filename = "APT_NGIN.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_NGIN.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("NGIN");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					System.out.println("total row count: "+rowCount);

					int colCount = sheet.getRow(0).getLastCellNum();

					
					System.out.println("Column count: "+colCount);
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}

			 
			 
		/**
		 * For Domain Management	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider
				public static Object[][] Finaldatareader_Domain() throws IOException {
					
					int count=0;
					
					String filename = "APT_DomainManagement.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_DomainManagement.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("DomainManagement");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					System.out.println("total row count: "+rowCount);

					int colCount = sheet.getRow(0).getLastCellNum();

					
					System.out.println("Column count: "+colCount);
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}
		
			 
		/**
		 * For Manage Network	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider
				public static Object[][] Finaldatareader_ManageNetwork() throws IOException {
					
					int count=0;
					
					String filename = "APT_ManageNetwork.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_ManageNetwork.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("ManageNetwork");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					System.out.println("total row count: "+rowCount);

					int colCount = sheet.getRow(0).getLastCellNum();

					
					System.out.println("Column count: "+colCount);
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}
			 
			 
			 
		/**
		 * For IP Transit	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider
				public static Object[][] Finaldatareader_IPTransit() throws IOException {
					
					int count=0;
					
					String filename = "APT_IPTransit.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPTransit.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("IPTransit");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					System.out.println("total row count: "+rowCount);

					int colCount = sheet.getRow(0).getLastCellNum();

					
					System.out.println("Column count: "+colCount);
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}
			 
			 
		/**
		 * For HSS	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider
				public static Object[][] Finaldatareader_HSS() throws IOException {
					
					int count=0;
					
					String filename = "APT_HSS.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_HSS.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("HSS");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					System.out.println("total row count: "+rowCount);

					int colCount = sheet.getRow(0).getLastCellNum();

					
					System.out.println("Column count: "+colCount);
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}
			
			 
			 
			/**
			 * For SAN Management 
			 * @return
			 * @throws IOException
			 */
			 @DataProvider
				public static Object[][] Finaldatareader_SANManagement() throws IOException {
					
					int count=0;
					
					String filename = "APT_SANManagement.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_SANManagement.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("SANMgmt");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					System.out.println("total row count: "+rowCount);

					int colCount = sheet.getRow(0).getLastCellNum();

					
					System.out.println("Column count: "+colCount);
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}
			 
			 
			 
			/**
			 * For NGIN Message 
			 * @return
			 * @throws IOException
			 */
			 @DataProvider
				public static Object[][] Finaldatareader_NGINMessage() throws IOException {
					
					int count=0;
					
					String filename = "APT_NGINMessage.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_NGINMessage.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("NGINMessage");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					System.out.println("total row count: "+rowCount);

					int colCount = sheet.getRow(0).getLastCellNum();

					
					System.out.println("Column count: "+colCount);
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}

			 
			 
			 /**
			  * For VOIP Access
			  * @return
			  * @throws IOException
			  */
			 @DataProvider
				public synchronized static Object[][] Finaldatareader_VOIP() throws IOException {
					
					int count=0;
					
					String filename = "APT_VOIP.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_VOIP.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("VOIP");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					int colCount = sheet.getRow(0).getLastCellNum();

					
					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
					}

					return obj;
				}


			 
			/**
			 * For DDI Range 
			 * @return
			 * @throws IOException
			 */
			 @DataProvider
				public synchronized static Object[][] DataReader_DDIRange() throws IOException {
					
					int count=0;
					
					String filename = "APT_DDIRange.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_DDIRange.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("DDI");
					
					int rowCountForMap = 0;

			        for(int k=1;k<=sheet.getLastRowNum();k++){
			                        XSSFRow counter=(XSSFRow) sheet.getRow(k);
			                        if(counter.getCell(0).toString().equalsIgnoreCase("Yes"))
			                        {
			                        	rowCountForMap=rowCountForMap+1;
			                        }
			        }


					int rowCount = sheet.getLastRowNum();
					
					int colCount = sheet.getRow(0).getLastCellNum();

					Object[][] obj = new Object[rowCountForMap][1];

					
					for (int i = 0; i < rowCount; i++) {

						Map<Object, Object> datamap = new HashMap<Object, Object>();
						
						if(sheet.getRow(i + 1).getCell(0).toString().equalsIgnoreCase("Yes")) {
							
							for (int j = 0; j < colCount; j++) {

								datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
							
							}
							
							obj[count][0] = datamap;
							count++;
						}
						else {
							System.out.println("No changes");
						}
						
					}

					return obj;
				}


	
	
}
