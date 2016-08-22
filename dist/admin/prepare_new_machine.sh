#!/bin/bash

# Create a wordarena user and log with it
#wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/create_user_wordarena.sh
#chmod +x create_user_wordarena.sh
#./create_user_wordarena.sh
#sudo -i -u wordarena
curl -sSL https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/create_user_wordarena.sh | sh

# Download all scripts
#wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_java.sh
#wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_mongodb.sh
#wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/setup_git.sh
#wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/fetch_dist.sh
#wget https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_wordarena.sh

# Make all scripts executable
#find . -name "*.sh" -exec chmod +x {} \;

# Install Java
curl -sSL https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_java.sh | sh

# Configure git
curl -sSL https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/setup_git.sh | sh

# Fetch wordarena distro
curl -sSL https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/fetch_dist.sh | sh

# Install MongoDB
curl -sSL https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_mongodb.sh | sh

# Install Wordarena service
curl -sSL https://raw.githubusercontent.com/opack/wordarena-server/master/dist/admin/install_wordarena.sh | sh

# Exit sudo wordarena
#exit