#!/bin/bash
# Updates wordarena using git dist project

# Stop the service
systemctl stop wordarena

# Backup the current version
SUFFIX=`date +%Y%m%d_%H%M%S`
sudo mv /opt/wordarena /opt/wordarena.${SUFFIX}

# Fetch the last version
/opt/wordarena.${SUFFIX}/admin/fetch_dist.sh

# Install the service
/opt/wordarena/admin/install_wordarena.sh