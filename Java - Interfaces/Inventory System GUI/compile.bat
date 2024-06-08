@echo off
echo Compilando o código Java...
javac -d bin src\com\devyat\inventorysystemgui\ProgramLauncher.java

if %errorlevel% neq 0 (
    echo Erro na compilação
    exit /b %errorlevel%
)

echo Compilação concluída com sucesso.
