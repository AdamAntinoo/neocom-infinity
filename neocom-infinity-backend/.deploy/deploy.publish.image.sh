# P U B L I S H    I M A G E
# Publish the image to the external repository
# - tag the image
echo ">>> Tagging image->adamantinoo/%_NEOCOM.TAG%"
docker tag %_NEOCOM.IMAGE_NAME% adamantinoo/%_NEOCOM.TAG%
docker tag %_NEOCOM.IMAGE_NAME% %_NEOCOM.TAG%
docker push adamantinoo/%_NEOCOM.TAG%
