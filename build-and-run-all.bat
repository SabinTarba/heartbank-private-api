@echo off
echo [1/2] Building Spring Boot project with Maven...
call .\mvnw clean package

if %ERRORLEVEL% NEQ 0 (
    echo Maven build failed. Aborting.
    exit /b %ERRORLEVEL%
)

echo [2/2] Starting Docker containers with Docker Compose...
docker compose -f docker-compose.yml up -d --build