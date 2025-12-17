@echo off
if not exist "bin" mkdir bin
echo Compilando...
javac -d bin -sourcepath src src/sistemabancario/*.java
if %errorlevel% neq 0 exit /b %errorlevel%
echo Executando...
java -cp bin sistemabancario.Programa
