#!/usr/bin/env bash
# Create a wordarena user
# Source : https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu-16-04
# Exécuter en tant que root

#WORDARENA_USER=wordarena
#WORDARENA_PASSWORD=JeSuisWordArena!33

# Create the user wordarena
#useradd $WORDARENA_USER --create-home
#echo $WORDARENA_USER:$WORDARENA_PASSWORD | chpasswd
adduser wordarena

# Authorize this user to run sudo commands
usermod -aG sudo $WORDARENA_USER