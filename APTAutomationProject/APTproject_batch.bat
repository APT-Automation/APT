@Echo off
SET LOGFILE2=C:\Users\SKathiresan-ADM\git\APTAutomation\APTAutomationProject\Logs\logfile.log
call :Logit >> %LOGFILE2%
exit /b 0
:Logit
set projectpath=C:\Users\SKathiresan-ADM\git\APTAutomation\APTAutomationProject
cd %projectpath%
set classpath=%projectpath%\bin;%projectpath%\lib\*
java org.testng.TestNG %projectpath%\testng.xml
pause 