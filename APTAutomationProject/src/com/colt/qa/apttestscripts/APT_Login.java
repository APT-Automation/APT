package com.colt.qa.apttestscripts;



import org.testng.annotations.Test;

import com.colt.qa.driverlibrary.DriverTestcase;

public class APT_Login extends DriverTestcase {

	@Test
	public void APT_Login_1(String url) throws Exception {
		
		APTLogin.get().Login(url);
        System.out.println("== APT app logged in successfully ===");
	}

}
