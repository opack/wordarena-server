#!/bin/bash
# Install wordarena daemon and starts it

# Copy service file
sudo cp /opt/wordarena/admin/wordarena.service /etc/systemd/system/wordarena.service

# Reload deamon files
systemctl daemon-reload

# Start service
sudo systemctl start wordarena

# Print service status
sudo systemctl status wordarena

# Enable automatically starting MongoDB when the system starts
sudo systemctl enable wordarena