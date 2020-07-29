ARG SDC_VERSION=latest
FROM streamsets/datacollector:${SDC_VERSION}

# Copy the stage libs and enterprise stage libs
COPY --chown=sdc:sdc streamsets-libs ${SDC_DIST}/streamsets-libs

# Copy the custom sdc.properties file into the image
COPY --chown=sdc:sdc sdc-conf/ ${SDC_CONF}/