#!/usr/bin/env bash

# This script builds a custom Docker image that extends the base SDC image
# It downloads a set of SDC stage libs and enterprise stage libs to a local directory. 
# The Dockerfile copies those libs into the SDC image as well as a custom sdc.properties file

# Your custom image
IMAGE_NAME=<your custom SDC image name>

# SDC Version
SDC_VERSION=3.16.1

# A space separated list of stage libs to download
SDC_STAGE_LIBS="streamsets-datacollector-aws-lib streamsets-datacollector-bigtable-lib streamsets-datacollector-google-cloud-lib streamsets-datacollector-groovy_2_4-lib streamsets-datacollector-jdbc-lib streamsets-datacollector-jms-lib streamsets-datacollector-jython_2_7-lib"

# A space separated list of enterprise stage libs to download
SDC_ENTERPRISE_STAGE_LIBS="streamsets-datacollector-databricks-lib-1.0.0 streamsets-datacollector-snowflake-lib-1.4.0 streamsets-datacollector-oracle-lib-1.2.0 streamsets-datacollector-sql-server-bdc-lib-1.0.1"

# Base URL to download SDC Stage Libs
BASE_URL=https://archives.streamsets.com/datacollector

# Use a tmp directory to unpack the downloaded stage libs
mkdir -p tmp-stage-libs
cd tmp-stage-libs

# Download and extract stage libs
for s in $SDC_STAGE_LIBS; 
do 
  wget ${BASE_URL}/${SDC_VERSION}/tarball/${s}-${SDC_VERSION}.tgz; 
  tar -xvf ${s}-${SDC_VERSION}.tgz;
  rm ${s}-${SDC_VERSION}.tgz;
done

# Download and extract enterprise stage libs
cd streamsets-datacollector-${SDC_VERSION}
for s in $SDC_ENTERPRISE_STAGE_LIBS; 
do 
  wget ${BASE_URL}/latest/tarball/enterprise/${s}.tgz; 
  tar -xvf ${s}.tgz; 
  rm -rf ${s}.tgz;
done

cd ../..

# move all the stage libs to the ./streamsets-libs dir
mv tmp-stage-libs/streamsets-datacollector-${SDC_VERSION}/streamsets-libs .

# remove tmp dir
rm -rf tmp-stage-libs

# Build the image
docker build -t $IMAGE_NAME .

# clean up 
rm -rf streamsets-libs

# Push the image
docker push $IMAGE_NAME