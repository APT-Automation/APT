@Echo off
title Create Customer Batch File 
SET LOGFILE=E:\Pramod_Workspace\APT_Automation_Demo1\TestLog.log
call :Logit >> %LOGFILE%
exit /b 0
:Logit
set projectpath=E:\Pramod_Workspace\APT_Automation_Demo1
cd %projectpath%
set classpath=%projectpath%\bin;%projectpath%\lib\*
java org.testng.TestNG %projectpath%\testng.xml
pause
