package com.saksoft.qa.apttestscripts;



import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.saksoft.qa.driverlibrary.DriverTestcase;

public class APT_Login extends DriverTestcase {

	@Test
	public void APT_Login_1() throws Exception {
	
//		
		
		APTLogin.get().Login("APT_login_1");
        System.out.println("== APT app logged in successfully ===");
        
        
	}

}
