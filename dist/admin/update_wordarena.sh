#!/bin/bash
# Updates wordarena using git dist project

# Stop the service (nécessite la saisie du mdp wordarena)
systemctl stop wordarena

# Backup the current version
SUFFIX=`date +%Y%m%d`
mv /opt/wordarena /opt/wordarena.${SUFFIX}

# Fetch the last version
fetch_dist.sh

# Install the service
./install_wordarena.sh