#!/bin/bash

# Make all scripts executable
find . -name "*.sh" -exec chmod +x {} \;

# Install Java
./install_java.sh

# Install MongoDB
./install_mongodb.sh

# Configure git
./setup_git.sh

# Fetch wordarena distro
./fetch_liv.sh

# Install Wordarena service
./install_wordarena.sh