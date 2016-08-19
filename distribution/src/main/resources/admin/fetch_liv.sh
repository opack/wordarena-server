#!/bin/bash
# Check out liv directory from Git repository
# Source : http://lakehanne.github.io/git-sparse-checkout

# make a directory we want to copy folders to
sudo mkdir /opt/wordarena/
cd /opt/wordarena/

#initialize the empty local repo
sudo git init

#add the remote origin
sudo git remote add origin -f https://github.com/opack/wordarena-server.git

#very crucial. this is where we tell git we are checking out specifics
sudo git config core.sparsecheckout true

#recursively checkout liv folder
sudo echo "liv/*" >> .git/info/sparse-checkout

#go only 2 depths down the examples directory
#git pull --depth=2 origin master
sudo git pull origin master