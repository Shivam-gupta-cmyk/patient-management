@echo off
setlocal

:: Ensure we detect the base dir properly
set DIR=%~dp0
if "%DIR%"=="" set DIR=%cd%

set MAVEN_PROJECTBASEDIR=%DIR%
if "%MAVEN_PROJECTBASEDIR:~-1%"=="\" set MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:~0,-1%

set WRAPPER_JAR=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\maven-wrapper.jar
set WRAPPER_LAUNCHER=org.apache.maven.wrapper.MavenWrapperMain

if not exist "%WRAPPER_JAR%" (
  echo The Maven Wrapper JAR is missing. Please re-run the wrapper setup.
  exit /b 1
)

set JAVA_EXE=java
"%JAVA_EXE%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECTBASEDIR%" -cp "%WRAPPER_JAR%" %WRAPPER_LAUNCHER% %*
endlocal
