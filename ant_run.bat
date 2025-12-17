@echo off
setlocal
:: Define local Ant path
set "ANT_HOME=%~dp0tools\ant\apache-ant-1.10.15"

:: Add Ant bin to PATH for this session
set "PATH=%ANT_HOME%\bin;%PATH%"

:: Run Ant with provided arguments
call ant %*
endlocal
