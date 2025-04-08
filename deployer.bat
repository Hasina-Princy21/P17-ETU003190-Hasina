@echo off

set "working_dir=."
set "local=."
set "webapps=C:\xampp\tomcat\webapps"
set "name=projet servlet"

if exist "%local%\build" (
    rmdir /s /q "%local%\build"
)

if exist "%local%\out" (
    rmdir /s /q "%local%\out"
)

mkdir "%local%\out"

mkdir "%local%\build"
mkdir "%local%\build\WEB-INF"
mkdir "%local%\build\WEB-INF\lib"
mkdir "%local%\build\WEB-INF\classes"

@REM xcopy /s /e /q "%working_dir%\src\webapp\*" "%local%\build\"
copy "%working_dir%\src\webapp\*.xml" "%local%\build\WEB-INF"
copy "%working_dir%\src\webapp\*.html" "%local%\build"
copy "%working_dir%\src\webapp\*.jsp" "%local%\build"

REM Copier le contenu du répertoire lib vers build/WEB-INF/lib
copy "%working_dir%\lib\*" "%local%\build\WEB-INF\lib"


for /r "%working_dir%\src\main\java" %%f in (*.java) do copy "%%f" "%local%\out"
for /r "%working_dir%\Societe\Base" %%f in (*.java) do copy "%%f" "%local%\out"
for /r "%working_dir%\Societe\DAO" %%f in (*.java) do copy "%%f" "%local%\out"
for /r "%working_dir%\Societe\entite" %%f in (*.java) do copy "%%f" "%local%\out"


javac -cp "%local%\build\WEB-INF\lib\*" -d "%local%\build\WEB-INF\classes" %local%\out\*.java


jar cvf "%local%\%name%.war" -C "%local%\build" .
move "%local%\%name%.war" "%webapps%"

@REM rmdir /s /q "%local%\build"
@REM rmdir /s /q "%local%\out"



