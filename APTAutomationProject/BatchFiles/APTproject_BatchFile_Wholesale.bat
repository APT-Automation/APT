@Echo off
cd..
SET LOGFILE2=D:\APT_phase1_deliverable24082020\APTAutomationProject\Logs\logfile.log
call :Logit >> %LOGFILE2%
exit /b 0
:Logit
set projectpath=D:\APT_phase1_deliverable24082020\APTAutomationProject
set classpath=%projectpath%\bin;%projectpath%\lib\*
java org.testng.TestNG Suite\wholesaleSIPtrunking.xml
pause 