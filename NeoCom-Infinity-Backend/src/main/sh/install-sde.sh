#!/bin/bash
figlet InstallSDE

./create-sde.sh

echo "> Moving database to destinations."
cp sde.db ../resources/
mv sde.db ../../integration/resources/
echo "> SDE db installation completed."
