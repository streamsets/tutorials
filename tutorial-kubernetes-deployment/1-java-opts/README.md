### How to set Java Heap size and other Java Options

Java options, like heap size, can be set at deployment time using the <code>SDC_JAVA_OPTS</code> environment variable in the deployment manifest like this:

    env:
    - name: SDC_JAVA_OPTS
      value: "-Xmx4g -Xms4g"
      
See [sdc.yaml](sdc.yaml) for an example SDC manifest with Java Opts settings.
 
