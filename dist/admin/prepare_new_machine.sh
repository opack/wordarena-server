#!/bin/bash

# Create a wordarena user and log with it
wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/create_user_wordarena.sh
./create_user_wordarena.sh

# Download all scripts
sudo -u wordarena wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_java.sh
sudo -u wordarena wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_mongodb.sh
sudo -u wordarena wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/setup_git.sh
sudo -u wordarena wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/fetch_liv.sh
sudo -u wordarena wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_wordarena.sh

# Make all scripts executable
find . -name "*.sh" -exec chmod +x {} \;

# Install Java
sudo -u wordarena ./install_java.sh

# Install MongoDB
sudo -u wordarena ./install_mongodb.sh

# Configure git
sudo -u wordarena ./setup_git.sh

# Fetch wordarena distro
sudo -u wordarena ./fetch_liv.sh

# Install Wordarena service
sudo -u wordarena ./install_wordarena.sh