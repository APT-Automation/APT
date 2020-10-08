package com.colt.qa.excellibrary;

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

public class DataReader {
	
	/**
	 * For Create Access Core Device_ Core Router
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_CoreRouter_Device",parallel=false) 
	public synchronized static Object[][] DataReader_CoreRouter_Device() throws IOException {
		
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
				////System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	/**
	 * For Create Access Core Device_ Voice Access DAS Gateway
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_VOIPAccessDASSwitch_Device",parallel=false) 
	public synchronized static Object[][] DataReader_VOIPAccessDASSwitch_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Voice Gateway
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_VoiceGateway_Device",parallel=false) 
	public synchronized static Object[][] DataReader_VoiceGateway_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Load Balancer
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_LoadBalancer_Device",parallel=false) 
	public synchronized static Object[][] DataReader_LoadBalancer_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Traffic Aggregator
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_TrafficAggregator_Device",parallel=false) 
	public synchronized static Object[][] DataReader_TrafficAggregator_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
		}

		return obj;
	}

	
	
	
	@DataProvider(name = "DataReader_RouterCPE_Device",parallel=false) 
	public synchronized static Object[][] DataReader_RouterCPE_Device() throws IOException {
		
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
	@DataProvider(name = "DataReader_DSLAM_Device",parallel=false) 
	public synchronized static Object[][] DataReader_DSLAM_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	/**
	 * For Create Access Core Device_ Mini DSLAM
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_MiniDSLAM_Device",parallel=false) 
	public synchronized static Object[][] DataReader_MiniDSLAM_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ Prizmnet
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_Prizmnet_Device",parallel=false) 
	public synchronized static Object[][] DataReader_Prizmnet_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}

	
	
	/**
	 * For Create Access Core Device_ MDF Firewall
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_MDFFirewall_Device",parallel=false) 
	public synchronized static Object[][] DataReader_MDFFirewall_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
		}

		return obj;
	}

	
		
	
	/**
	 * For Create Access Core Device _ Key Server device
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_Keyserver_Device",parallel=false) 
	public synchronized static Object[][] DataReader_Keyserver_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}
	
	
	
	/**
	 * For Create Access Core Device _ DCN device
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_DCNDevice_Device",parallel=false) 
	public synchronized static Object[][] DataReader_DCNDevice_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}
	
	
	
//	@DataProvider(name = "DataReader_CoreRouter_Device",parallel=false) 
//	public synchronized static Object[][] DataReader_CoreRouter_Device() throws IOException {
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
	
	
	
	@DataProvider(name = "DataReader_AccessSwitch_Device",parallel=false) 
	public synchronized static Object[][] DataReader_AccessSwitch_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}
	

		
	/*
	 * For Create Access Core Device _ Access Router
	 */
		@DataProvider(name = "DataReader_AccessRouter_Device",parallel=false) 
		public synchronized static Object[][] DataReader_AccessRouter_Device() throws IOException {
			
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
					//System.out.println("No changes");
				}
				
			}

			return obj;
		}

	
	
	
	
	@DataProvider(name = "Read_CreateDeviceTestdata",parallel=false) 
	public synchronized static Object[][] Read_CreateDeviceTestdata() throws IOException {
		
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
	public synchronized static Object[][] Read_EditDeviceTestdata() throws IOException {
		
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
	public synchronized static Object[][] Finaldatareader_CreateOrder_NGIN_Customer() throws IOException {
		
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
	
	
	@DataProvider(name = "Finaldatareader_CreateOrder_NGIN",parallel=false) 
	public synchronized static Object[][] Finaldatareader_CreateOrder_NGIN() throws IOException {
		
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
	
	
	
	
	
	@DataProvider(name = "Finaldatareader_CreateOrder_WholesaleSIPTrunk",parallel=false) 
	public synchronized static Object[][] Finaldatareader_CreateOrder_WholesaleSIPTrunk() throws IOException {
		
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
	
	
	
	/**
	 * For Create Access Core Device_ DSLAM
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "DataReader_Firewall_Device",parallel=false) 
	public synchronized static Object[][] DataReader_Firewall_Device() throws IOException {
		
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
				//System.out.println("No changes");
			}
			
		}

		return obj;
	}


	
	//Search Device
		@DataProvider(name = "DataReader_SearchDevice",parallel=false) 
		public synchronized static Object[][] DataReader_SearchDevice() throws IOException {
			
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
		 @DataProvider(name = "DataReader_ManagePostcode",parallel=false) 
			public synchronized static Object[][] DataReader_ManagePostcode() throws IOException {
				
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
						//System.out.println("No changes");
					}
					
				}

				return obj;
			}
		 
		 
		 /**
		  * Manage Number Translation data reader
		  * @return
		  * @throws IOException
		  */
		 @DataProvider(name = "DataReader_ManageTranslation",parallel=false) 
		    public synchronized static Object[][] DataReader_ManageTranslation() throws IOException {

				
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
						//System.out.println("No changes");
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
			@DataProvider(name = "Finaldatareader_wholeSale",parallel=false) 
			public synchronized static Object[][] Finaldatareader_wholeSale() throws IOException {
				
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
						//System.out.println("No changes");
					}
					
				}

				return obj;
			}
			
			
			/**
			 * For Customer User
			 * @return
			 * @throws IOException
			 */
			@DataProvider(name = "DataReader_CustomerFunctionality",parallel=false) 
			public synchronized static Object[][] DataReader_CustomerFunctionality() throws IOException {
				
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
						//System.out.println("No changes");
					}
				}
				return obj;
			}

			
			
			/**
			 * For Customer User _ Perform Supply Operation
			 * @return
			 * @throws IOException
			 */
			@DataProvider(name = "DataReader_CreateCustomer_AddUser_SupplyService",parallel=false) 
			public synchronized static Object[][] DataReader_CreateCustomer_AddUser_SupplyService() throws IOException {
				
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
						//System.out.println("No changes");
					}
				}
				return obj;
			}
			
			
/**
 * For NGIN
 * @return
 * @throws IOException
 */
			 @DataProvider(name = "Finaldatareader_NGIN",parallel=false) 
				public synchronized static Object[][] Finaldatareader_NGIN() throws IOException {
					
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
							//System.out.println("No changes");
						}
						
					}

					return obj;
				}

			 
			 
		/**
		 * For Domain Management	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider(name = "Finaldatareader_Domain",parallel=false) 
				public synchronized static Object[][] Finaldatareader_Domain() throws IOException {
					
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
							//System.out.println("No changes");
						}
						
					}

					return obj;
				}
		
			 
		/**
		 * For Manage Network	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider(name = "Finaldatareader_ManageNetwork",parallel=false) 
				public synchronized static Object[][] Finaldatareader_ManageNetwork() throws IOException {
					
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
							//System.out.println("No changes");
						}
						
					}

					return obj;
				}
			 
			 
			 
		/**
		 * For IP Transit	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider(name = "Finaldatareader_IPTransit",parallel=false) 
				public synchronized static Object[][] Finaldatareader_IPTransit() throws IOException {
					
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
							//System.out.println("No changes");
						}
						
					}

					return obj;
				}
			 
			 
		/**
		 * For HSS	 
		 * @return
		 * @throws IOException
		 */
			 @DataProvider(name = "Finaldatareader_HSS",parallel=false) 
				public synchronized static Object[][] Finaldatareader_HSS() throws IOException {
					
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
							//System.out.println("No changes");
						}
						
					}

					return obj;
				}
			
			 
			 
			/**
			 * For SAN Management 
			 * @return
			 * @throws IOException
			 */
			 @DataProvider(name = "Finaldatareader_SANManagement",parallel=false) 
				public synchronized static Object[][] Finaldatareader_SANManagement() throws IOException {
					
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
							//System.out.println("No changes");
						}
						
					}

					return obj;
				}
			 
			 
			 
			/**
			 * For NGIN Message 
			 * @return
			 * @throws IOException
			 */
			 @DataProvider(name = "Finaldatareader_NGINMessage",parallel=false) 
				public synchronized static Object[][] Finaldatareader_NGINMessage() throws IOException {
					
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
							//System.out.println("No changes");
						}
						
					}

					return obj;
				}

			 
			 
			 /**
			  * For VOIP Access
			  * @return
			  * @throws IOException
			  */
			 @DataProvider(name = "Finaldatareader_VOIP",parallel=false) 
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
							//System.out.println("No changes");
						}
					}

					return obj;
				}


			 
			/**
			 * For DDI Range 
			 * @return
			 * @throws IOException
			 */
			 @DataProvider(name = "DataReader_DDIRange",parallel=false) 
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
							//System.out.println("No changes");
						}
					}
					return obj;
				}
			 
			 
			 /**
				 * For Lanlink Direct Fiber
				 * @return
				 * @throws IOException
				 */
				@DataProvider(name = "DataReader_LANLINK_DirectFiber",parallel=false) 
				public synchronized static Object[][] DataReader_LANLINK_DirectFiber() throws IOException {
					
					int count=0;
					
					String filename = "APT_LANLINK.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_LANLINK.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("DirectFiber");
					
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
							//System.out.println("No changes");
						}
					}
					return obj;
				}

				
				
				 /**
				 * For Lanlink International
				 * @return
				 * @throws IOException
				 */
				@DataProvider(name = "DataReader_LANLINK_International",parallel=false) 
				public synchronized static Object[][] DataReader_LANLINK_International() throws IOException {
					
					int count=0;
					
					String filename = "APT_LANLINK.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_LANLINK.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("International");
					
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
							//System.out.println("No changes");
						}
					}
					return obj;
				}

				
				 /**
				 * For Lanlink National
				 * @return
				 * @throws IOException
				 */
				@DataProvider(name = "DataReader_LANLINK_National",parallel=false) 
				public synchronized static Object[][] DataReader_LANLINK_National() throws IOException {
					
					int count=0;
					
					String filename = "APT_LANLINK.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_LANLINK.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("National");
					
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
							//System.out.println("No changes");
						}
					}
					return obj;
				}

				
				

				 /**
				 * For Lanlink Metro
				 * @return
				 * @throws IOException
				 */
				@DataProvider(name = "DataReader_LANLINK_Metro",parallel=false) 
				public synchronized static Object[][] DataReader_LANLINK_Metro() throws IOException {
					
					int count=0;
					
					String filename = "APT_LANLINK.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_LANLINK.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("Metro");
					
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
							//System.out.println("No changes");
						}
					}
					return obj;
				}


				 /**
				 * For Lanlink OLO
				 * @return
				 * @throws IOException
				 */
				@DataProvider(name = "DataReader_LANLINK_OLO",parallel=false) 
				public synchronized static Object[][] DataReader_LANLINK_OLO() throws IOException {
					
					int count=0;
					
					String filename = "APT_LANLINK.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_LANLINK.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("OLO");
					
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
							//System.out.println("No changes");
						}
					}
					return obj;
				}
				
				
				 /**
				 * For Lanlink outBandManagement
				 * @return
				 * @throws IOException
				 */
				@DataProvider(name = "DataReader_LANLINK_outBandManagement",parallel=false) 
				public synchronized static Object[][] DataReader_LANLINK_outBandManagement() throws IOException {
					
					int count=0;
					
					String filename = "APT_LANLINK.xlsx";

					File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_LANLINK.xlsx");
					
					FileInputStream inputStream = new FileInputStream(file);

					Workbook workbook = null;

					String fileExtensionName = filename.substring(filename.indexOf("."));

					if (fileExtensionName.equals(".xlsx")) {

						workbook = new XSSFWorkbook(inputStream);

					}

					else if (fileExtensionName.equals(".xls")) {

						workbook = new HSSFWorkbook(inputStream);

					}

					Sheet sheet = workbook.getSheet("Outband");
					
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
							//System.out.println("No changes");
						}
					}
					return obj;
				}
				
				
				/**
				 * For Voice Line (V)
				 * @return
				 * @throws IOException
				 */
				 @DataProvider(name = "Finaldatareader_VoiceLine",parallel=false) 
					public synchronized static Object[][] Finaldatareader_VoiceLine() throws IOException {
						
						int count=0;
						
						String filename = "APT_VoiceLine.xlsx";

						File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_VoiceLine.xlsx");
						
						FileInputStream inputStream = new FileInputStream(file);

						Workbook workbook = null;

						String fileExtensionName = filename.substring(filename.indexOf("."));

						if (fileExtensionName.equals(".xlsx")) {

							workbook = new XSSFWorkbook(inputStream);

						}

						else if (fileExtensionName.equals(".xls")) {

							workbook = new HSSFWorkbook(inputStream);

						}

						Sheet sheet = workbook.getSheet("VoiceLine");
						
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
								//System.out.println("No changes");
							}
						}
						return obj;
					}


				 
				 /**
					 * Search For Device
					 * @return
					 * @throws IOException
					 */
					 @DataProvider(name = "Finaldatareader_searchDevice",parallel=false) 
						public synchronized static Object[][] Finaldatareader_searchDevice() throws IOException {
							
							int count=0;
							
							String filename = "APT_searchForDevice.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_searchForDevice.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("searchDevice");
							
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
									//System.out.println("No changes");
								}
							}
							return obj;
						}

					 
					 
					 @DataProvider(name = "Finaldatareader_IPVPNPlus",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNPlus() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("IPVPN Plus");
							
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

					 @DataProvider(name = "Finaldatareader_IPVPNAccess",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNAccess() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("IPVPN Access");
							
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

					 @DataProvider(name = "Finaldatareader_IPVPNConnect",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNConnect() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("IPVPN Connect");
							
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


					 
					 
					 @DataProvider(name = "Finaldatareader_IPVPNIPSec",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNIPSec() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("IPVPN IPSec");
							
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
					 
					 
					 

					 @DataProvider(name = "Finaldatareader_IPVPNCPESolutionL2",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNCPESolutionL2() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("CPE Solution L2");
							
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

					 
					 
					 @DataProvider(name = "Finaldatareader_IPVPNCPESolutionL3",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNCPESolutionL3() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("CPE Solution L3");
							
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
					 
					 
					 @DataProvider(name = "Finaldatareader_IPVPNIPVoice",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNIPVoice() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("IP Voice");
							
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

					 
					 
					 @DataProvider(name = "Finaldatareader_IPVPNSwiftNet",parallel=false) 
						public synchronized static Object[][] Finaldatareader_IPVPNSwiftNet() throws IOException {
							int count=0;
							String filename = "APT_CreateOrder_IPVPN.xlsx";

							File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_IPVPN.xlsx");
							
							FileInputStream inputStream = new FileInputStream(file);

							Workbook workbook = null;

							String fileExtensionName = filename.substring(filename.indexOf("."));

							if (fileExtensionName.equals(".xlsx")) {

								workbook = new XSSFWorkbook(inputStream);

							}

							else if (fileExtensionName.equals(".xls")) {

								workbook = new HSSFWorkbook(inputStream);

							}

							Sheet sheet = workbook.getSheet("SwiftNet");
							
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


				 
	
}
