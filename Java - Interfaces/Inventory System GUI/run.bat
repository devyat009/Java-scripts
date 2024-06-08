@echo off
echo Executando o programa Java...
java -cp bin com.devyat.inventorysystemgui.ProgramLauncher

if %errorlevel% neq 0 (
    echo Erro na execução
    exit /b %errorlevel%
)

echo Programa finalizado com sucesso.
