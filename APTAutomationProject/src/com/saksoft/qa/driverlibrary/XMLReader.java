package com.saksoft.qa.driverlibrary;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLReader {
	
	String fileloaction;
	public XMLReader(String filepath)
	{
		fileloaction=filepath;
	}
	
public String getlocator(String nodepath) throws DocumentException, InterruptedException
{
	String locator=null;
	File Inputfile=new File(fileloaction);
	SAXReader xmlreader=new SAXReader();
	Document doc=xmlreader.read(Inputfile);
	Thread.sleep(3000);
	//Document doc= 'E:\APT_Supraja_Workspace\APT_Automation_Demo_2\src\com\saksoft\qa\pagerepository\APT_MCS_CreateOrder_NGIN_ServiceType.xml';
	locator=doc.selectSingleNode(nodepath).getText();
	Log.info(locator);
	return locator;
	
}



}


