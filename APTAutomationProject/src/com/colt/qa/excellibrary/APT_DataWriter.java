package com.colt.qa.excellibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.DocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.colt.qa.driverlibrary.DriverHelper;

public class APT_DataWriter {

	public static void main(String[] args) throws IOException {
		
		DataWriter_PK();
	}
	
	
	
	public static void DataWriter_PK() throws IOException {
		
		String filename="sampleDate.xlsx";
		String sheetName="Dataentry";
		String[] dataToWrite = {"andry","simmons", "paul"};
//		String[] dataToWrite = {"testpk27",	"testpk27.com",	"AT (Austria)",	"testocn1",	"testreference1",	"testerSon1",	"Colt Telecom - internal", "testpk27@test.com",	"9876543210",	"11223344", "Created", date};

		
		 File file =    new File("C:\\Users\\sivas.k\\eclipse-workspace\\APTLearning\\src\\Data\\sampleDate.xlsx");
		
		 FileInputStream inputStream = new FileInputStream(file);
		 
		 Workbook workbook = null;
		 
		 String fileExtensionName = filename.substring(filename.indexOf("."));
		 
		 System.out.println("file extension name is : "+ fileExtensionName);
		 
		
		 if(fileExtensionName.equals(".xlsx")){
			 
			 workbook = new XSSFWorkbook(inputStream);
			 
			 System.out.println("The workbook used is in XLSX format");

		        }
		 
		 else if(fileExtensionName.equals(".xls")){

			 workbook = new HSSFWorkbook(inputStream);
			 
			 System.out.println("The workbook used is in XLS format");

	        }    
		 
		 
		 Sheet sheet = workbook.getSheet(sheetName);
		 
		 System.out.println("The sheet name is : "+sheet.getSheetName());
		 
		 int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		 		 
		 Row row = sheet.getRow(0);
		 
		 Row newRow = sheet.createRow(rowCount+1);
		 
		 for(int j = 0; j < row.getLastCellNum(); j++){

		        Cell cell = newRow.createCell(j);

		        cell.setCellValue(dataToWrite[j]);

		        System.out.println("Data to be entered are: "+dataToWrite[j]);
		    }

		 inputStream.close();
		 
		 System.out.println("Till here working fine");

		    FileOutputStream outputStream = new FileOutputStream(file);

		    workbook.write(outputStream);

		    outputStream.close();
	}
	
	
	
	static WebDriver dr;
	public static void DataWriter2_CreatedCustomer_PK(String[] dataToWrite) throws IOException, InterruptedException, DocumentException {
			String filename="APT_MCS_TESTDATA_PK.xlsx";
			String sheetName="EditCustomerDataEntry";
			String date=DriverHelper.getCurrentDate(); // Added By PK
//			String[] dataToWrite = {"testpk27",	"testpk27.com",	"AT (Austria)",	"testocn1",	"testreference1",	"testerSon1",	"Colt Telecom - internal", "testpk27@test.com",	"9876543210",	"11223344", "Created", date};
					
					
			 File file = new File("src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx");
			
			 FileInputStream inputStream = new FileInputStream(file);
			 
			 Workbook workbook = null;
			 
			 String fileExtensionName = filename.substring(filename.indexOf("."));
			 
			 System.out.println("file extension name is : "+ fileExtensionName);
			 
			
			 if(fileExtensionName.equals(".xlsx")){
				 
				 workbook = new XSSFWorkbook(inputStream);
				 
				 System.out.println("The workbook used is in XLSX format");

			        }
			 
			 else if(fileExtensionName.equals(".xls")){

				 workbook = new HSSFWorkbook(inputStream);
				 
				 System.out.println("The workbook used is in XLS format");

		        }    
			 
			 
			 Sheet sheet = workbook.getSheet(sheetName);
			 
			 System.out.println("The sheet name is : "+sheet.getSheetName());
			 
			 int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
			 		 
			 Row row = sheet.getRow(0);
			 
			 Row newRow = sheet.createRow(rowCount+1);
			 
			 for(int j = 0; j < row.getLastCellNum(); j++){

			        Cell cell = newRow.createCell(j);

			        cell.setCellValue(dataToWrite[j]);

			        System.out.println("Data to be entered are: "+dataToWrite[j]);
			    }

			 inputStream.close();
			 
			 System.out.println("Till here working fine");

			    FileOutputStream outputStream = new FileOutputStream(file);

			    workbook.write(outputStream);

			    outputStream.close();
		}

	
	
	

public static void DataWriter3_UpdatedCustomer_PK() throws IOException {
	String filename="APT_MCS_TESTDATA_PK.xlsx";
	String sheetName="EditCustomerDataEntry";
	String date=DriverHelper.getCurrentDate(); // Added By PK
	//System.out.println("Date :  "+date);
	String[] dataToWrite = {"testpk28",	"testpk28.com",	"AT (Austria)",	"testocn1",	"testreference1",	"testerSon1",	"Partner", "testpk27@test.com",	"9876543210",	"11223344", "Updated", date};
	
	 File file =    new File("C:\\Users\\pramod.k\\eclipse-workspace\\APT_Automation_M3\\src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx");
	
	 FileInputStream inputStream = new FileInputStream(file);
	 
	 Workbook workbook = null;
	 
	 String fileExtensionName = filename.substring(filename.indexOf("."));
	 
	 System.out.println("file extension name is : "+ fileExtensionName);
	 
	
	 if(fileExtensionName.equals(".xlsx")){
		 
		 workbook = new XSSFWorkbook(inputStream);
		 
		 System.out.println("The workbook used is in XLSX format");

	        }
	 
	 else if(fileExtensionName.equals(".xls")){

		 workbook = new HSSFWorkbook(inputStream);
		 
		 System.out.println("The workbook used is in XLS format");

        }    
	 
	 
	 Sheet sheet = workbook.getSheet(sheetName);
	 
	 System.out.println("The sheet name is : "+sheet.getSheetName());
	 
	 int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	 		 
	 Row row = sheet.getRow(0);
	 
	 Row newRow = sheet.createRow(rowCount+1);
	 
	 for(int j = 0; j < row.getLastCellNum(); j++){

	        Cell cell = newRow.createCell(j);

	        cell.setCellValue(dataToWrite[j]);

	        System.out.println("Data to be updated are: "+dataToWrite[j]);
	    }

	 inputStream.close();
	 
	 System.out.println("Till here working fine");

	    FileOutputStream outputStream = new FileOutputStream(file);

	    workbook.write(outputStream);

	    outputStream.close();
}



public static void CreateCustomerDataWriter(String[] dataToWrite) throws IOException {
	
	String filename = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";
	
	 File file =    new File("src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx");
	 
	 FileInputStream inputStream = new FileInputStream(file);
	 Workbook workbook = null;
	 String fileExtensionName = filename.substring(filename.indexOf("."));
	 System.out.println("file extension name is : "+ fileExtensionName);
	 
	 if(fileExtensionName.equals(".xlsx")){
		 workbook = new XSSFWorkbook(inputStream);
		 System.out.println("The workbook used is in XLSX format");
	        }
	 
	 else if(fileExtensionName.equals(".xls")){

		 workbook = new HSSFWorkbook(inputStream);
		 
		 System.out.println("The workbook used is in XLS format");

        }    
	 
	 
	 Sheet sheet = workbook.getSheet("EditCustomerDataEntry");
	 
	 System.out.println("The sheet name is : "+sheet.getSheetName());
	 
	 int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	 
	 System.out.println("The row count is: "+rowCount);
	 		 
	 Row row = sheet.getRow(0);
	 
	 Row newRow = sheet.createRow(rowCount+1);
	 
	 System.out.println("====The last cell number is: =========="+row.getLastCellNum());
	 
	 for(int j = 0; j < row.getLastCellNum(); j++){

	        Cell cell = newRow.createCell(j);

	        cell.setCellValue(dataToWrite[j]);

	        System.out.println("Data to be entered are: "+dataToWrite[j]);
	    }

	 inputStream.close();
	 
	 System.out.println("Till here working fine");

	    FileOutputStream outputStream = new FileOutputStream(file);

	    workbook.write(outputStream);

	    outputStream.close();	 
}



public static void CreateUserDataWriter(String[] dataToWrite) throws IOException {
	
	String filename = "src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx";
	//	String[] dataToWrite = {"RouterDevice","Trafic", "UserId", "fdjbk", "12", "on", "on", "wwg", "India"};
	
	 File file =    new File("src\\com\\colt\\qa\\datalibrary\\APT_MCS_TESTDATA_PK.xlsx");
	 
	 FileInputStream inputStream = new FileInputStream(file);
	 Workbook workbook = null;
	 String fileExtensionName = filename.substring(filename.indexOf("."));
	 System.out.println("file extension name is : "+ fileExtensionName);
	 
	 if(fileExtensionName.equals(".xlsx")){
		 workbook = new XSSFWorkbook(inputStream);
		 System.out.println("The workbook used is in XLSX format");
	        }
	 
	 else if(fileExtensionName.equals(".xls")){

		 workbook = new HSSFWorkbook(inputStream);
		 
		 System.out.println("The workbook used is in XLS format");

        }    
	 
	 
	 Sheet sheet = workbook.getSheet("EditUserDataEntry");
	 
	 System.out.println("The sheet name is : "+sheet.getSheetName());
	 
	 int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	 
	 System.out.println("The row count is: "+rowCount);
	 		 
	 Row row = sheet.getRow(0);
	 
	 Row newRow = sheet.createRow(rowCount+1);
	 
	 System.out.println("====The last cell number is: =========="+row.getLastCellNum());
	 
	 for(int j = 0; j < row.getLastCellNum(); j++){

	        Cell cell = newRow.createCell(j);

	        cell.setCellValue(dataToWrite[j]);

	        System.out.println("Data to be entered are: "+dataToWrite[j]);
	    }

	 inputStream.close();
	 
	 System.out.println("Till here working fine");

	    FileOutputStream outputStream = new FileOutputStream(file);

	    workbook.write(outputStream);

	    outputStream.close();	 
}








	
}
