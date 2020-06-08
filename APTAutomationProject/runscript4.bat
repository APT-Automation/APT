set projectpath=E:\Pramod_Workspace\APT_Automation_Demo1
cd %projectpath%
set classpath=%projectpath%\bin;%projectpath%\lib\*
java org.testng.TestNG %projectpath%\testng.xml
pause