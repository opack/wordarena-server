#!/bin/bash
# Check out liv directory from Git repository
# Source : http://lakehanne.github.io/git-sparse-checkout

# make a directory we want to copy folders to
sudo mkdir /opt/wordarena/
sudo chown wordarena:wordarena /opt/wordarena
cd /opt/wordarena/

#initialize the empty local repo
git init

#add the remote origin
git remote add origin -f https://github.com/opack/wordarena-server.git

#very crucial. this is where we tell git we are checking out specifics
git config core.sparsecheckout true

#recursively checkout liv folder
echo "dist/*" >> .git/info/sparse-checkout

#go only 2 depths down the examples directory
#git pull --depth=2 origin master
git pull origin master

# Move dist content to current directory and then remove empty dist folder
mv dist/* .
rm -r dist

# Make all scripts executable
find . -name "*.sh" -exec chmod +x {} \;

# Go back to home directory
cd ~