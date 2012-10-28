call ant
copy .\dist\*.zip "%USERPROFILE%\.BuildServer\plugins\"  /Y
rem call %TEAMCITY_HOME%\buildAgent\bin\agent.bat stop
rem CHOICE /C:AB /D:A /T:2 >NUL
rem call %TEAMCITY_HOME%\buildAgent\bin\agent.bat start