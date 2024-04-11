#!/bin/bash
# - PARAMETERS & CONSTANTS
ENVIRONMENT=$1
if [ -z "$ENVIRONMENT" ]; then
  ENVIRONMENT="staging"
fi

export ENVIRONMENT
export APPLICATION_JAR_NAME="neocom-infinity-backend"
export APPLICATION_CODE="NIB"
export SEMVER=`gitversion /showvariable MajorMinorPatch`
export VMETA=`gitversion /showvariable CommitsSinceVersionSource`
export VERSION=$SEMVER-$VMETA

# - Startup
figlet "Build $APPLICATION_CODE"
figlet $VERSION
echo "Environment            $ENVIRONMENT"
echo "Version                $VERSION"

WORKING_DIRECTORY="$(dirname "$0")"
echo "Working Directory      `pwd`"
PROJECT_ROOT=../..
PROJECT_NAME=neocom-infinity-backend
SOURCE_DIRECTORY=$PROJECT_ROOT/$PROJECT_NAME
cd $SOURCE_DIRECTORY
echo "Source Directory       `pwd`"
cd -

exit 0
