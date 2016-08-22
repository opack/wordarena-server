#!/bin/bash

# Create a wordarena user and log with it
wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/create_user_wordarena.sh
chmod +x create_user_wordarena.sh
./create_user_wordarena.sh
sudo -i -u wordarena

# Download all scripts
wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_java.sh
wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_mongodb.sh
wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/setup_git.sh
wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/fetch_liv.sh
wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_wordarena.sh

# Make all scripts executable
find . -name "*.sh" -exec chmod +x {} \;

# Install Java
./install_java.sh

# Configure git
./setup_git.sh

# Fetch wordarena distro
fetch_dist.sh

# Install MongoDB
./install_mongodb.sh

# Install Wordarena service
./install_wordarena.sh

# Exit sudo wordarena
exit