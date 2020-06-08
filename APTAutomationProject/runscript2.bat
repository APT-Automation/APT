@Echo off
SET LOGFILE1=E:\Pramod_Workspace\APT_Automation_Demo1\TestLog.log
call :Logit >> %LOGFILE1%
SET LOGFILE2=E:\Pramod_Workspace\APT_Automation_Demo1\Logs\logfile.log
call :Logit >> %LOGFILE2%
exit /b 0
:Logit
set projectpath=E:\Pramod_Workspace\APT_Automation_Demo1
cd %projectpath%
set classpath=%projectpath%\bin;%projectpath%\lib\*
java org.testng.TestNG %projectpath%\testng.xml
pause