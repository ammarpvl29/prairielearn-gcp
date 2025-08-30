#!/bin/bash

# Setup script to download JUnit 5 Console Launcher
# This should be run once during container setup

echo "Setting up Java testing environment..."

# Download JUnit Platform Console Standalone JAR if it doesn't exist
if [ ! -f "junit-platform-console-standalone.jar" ]; then
    echo "Downloading JUnit Platform Console Standalone..."
    wget -O junit-platform-console-standalone.jar "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar"
    echo "JUnit Console Launcher downloaded successfully"
else
    echo "JUnit Console Launcher already exists"
fi

# Make sure bc calculator is available for score calculation
if ! command -v bc &> /dev/null; then
    echo "Installing bc calculator..."
    apt-get update && apt-get install -y bc
fi

echo "Setup complete!"