#!/bin/bash
# Install MongoDB
# Source : https://www.digitalocean.com/community/tutorials/how-to-install-mongodb-on-ubuntu-16-04

# Add the MongoDB repository
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927
echo "deb http://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.2 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.2.list
sudo apt-get update

# Install and verify MongoDB
sudo apt-get install -y mongodb-org

# Copy service file
sudo cp /opt/wordarena/admin/wordarena.service /etc/systemd/system/wordarena.service

# Reload deamon files
systemctl daemon-reload

# Start service
sudo systemctl start mongodb

# Print service status
sudo systemctl status mongodb

# Enable automatically starting MongoDB when the system starts
sudo systemctl enable mongodb