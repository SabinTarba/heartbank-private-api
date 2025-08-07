@echo off

echo [1/3] Building Spring Boot project with Maven...
call .\mvnw clean package

if %ERRORLEVEL% NEQ 0 (
    echo Maven build failed. Aborting.
    exit /b %ERRORLEVEL%
)

echo [2/3] Building new Docker image...
docker-compose build heartbank-private-api

if %ERRORLEVEL% NEQ 0 (
    echo Docker build failed. Aborting.
    exit /b %ERRORLEVEL%
)

echo [3/3] Restarting Spring Boot Application...
docker-compose up -d heartbank-private-api