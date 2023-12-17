#!/bin/bash

# Color codes
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Function to clean up containers and exit
cleanup() {
    echo -e "${GREEN}Cleaning up containers...${NC}"
    docker stop $(docker ps -q -f "ancestor=my_combined_app")
    exit
}

# Trap the INT signal (Ctrl+C) to call the cleanup function
trap 'cleanup' INT

# Build the frontend
echo -e "${GREEN}Building the frontend...${NC}"
cd frontend
npm install
npm run build
cd ..

# Build the Docker image for the combined app
echo -e "${GREEN}Building the Docker image...${NC}"
docker build -t my_combined_app .

# Run the Docker container
echo -e "${GREEN}Running the Docker container...${NC}"
docker run -p 8080:8080 my_combined_app &

# Provide instructions to the user
echo -e "${YELLOW}Your Dockerized combined application is running!"
echo -e "Access it at ${GREEN}http://localhost:8080${NC}"
echo -e "${YELLOW}Press Ctrl+C to stop the application.${NC}"

# Wait for the user to stop the application
wait
