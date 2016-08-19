#!/bin/bash

# Download all scripts
wget https://github.com/opack/wordarena-server/tree/master/dist/admin/create_user_wordarena.sh
wget https://github.com/opack/wordarena-server/tree/master/dist/admin/install_java.sh
wget https://github.com/opack/wordarena-server/tree/master/dist/admin/install_mongodb.sh
wget https://github.com/opack/wordarena-server/tree/master/dist/admin/setup_git.sh
wget https://github.com/opack/wordarena-server/tree/master/dist/admin/fetch_liv.sh
wget https://github.com/opack/wordarena-server/tree/master/dist/admin/install_wordarena.sh

# Make all scripts executable
find . -name "*.sh" -exec chmod +x {} \;

# Create a wordarena user and log with it
./create_user_wordarena.sh

# Install Java
#sudo -u wordarena ./install_java.sh

# Install MongoDB
#sudo -u wordarena ./install_mongodb.sh

# Configure git
#sudo -u wordarena ./setup_git.sh

# Fetch wordarena distro
#sudo -u wordarena ./fetch_liv.sh

# Install Wordarena service
#sudo -u wordarena ./install_wordarena.sh