@Echo off
cd..
SET LOGFILE2=D:\APT_Phase1_Automation_Project_Deliverables\APTAutomationProject\Logs\consoleLogger.log
call :Logit >> %LOGFILE2%
exit /b 0
:Logit
set projectpath=D:\APT_Phase1_Automation_Project_Deliverables\APTAutomationProject
set classpath=%projectpath%\bin;%projectpath%\lib\*;%projectpath%\reportng\*
java org.testng.TestNG Suite\HSS.xml 