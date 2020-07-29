### Loading <code>sdc.properties</code> from a ConfigMap

An approach that offers greater flexibility than "baking-in" the <code>sdc.properties</code> file (as in the [Custom Docker Image example](../2-custom-docker-image)) is to dynamically mount an <code>sdc.properties</code> file at deployment time. One way to do that is to store an <code>sdc.properties</code> file in a configMap and to Volume Mount the configMap into the SDC container, overwriting the default <code>sdc.properties</code> file packaged with the image.

The configMap's representation of <code>sdc.properties</code> will be read-only, so one can't use any <code>SDC_CONF_</code> prefixed environment variables in the SDC deployment (see [this note](../NoteOnEnvVars.md)); all custom property values for properties defined in <code>sdc.properties</code> need to be set in the  configMap (though one can still set <code>SDC_JAVA_OPTS</code> in the environment as that is a "pure" environment variable used by SDC).  

This example uses one monolithic <code>sdc.properties</code> file stored in a single configMap (see the example [here](../6-sdc-properties-configmap-2) for a more modular approach).

Start by copying a clean <code>sdc.properties</code> file to a local working directory. Edit the property values you want for a given deployment.  For example, I will edit these properties within the file:

    sdc.base.http.url=https://sequoia.onefoursix.com
    http.enable.forwarded.requests=true
    http.realm.file.permission.check=false  # set this to avoid permission issues
    production.maxBatchSize=20000 
    
Save the edited <code>sdc.properties</code> file in a configMap named <code>sdc-properties</code> by executing the command:

    $ kubectl create configmap sdc-properties --from-file=sdc.properties

Create the configMap prior to starting the SDC deployment.

Add the configMap as a Volume in your SDC deployment manifest like this:

    volumes:
    - name: sdc-properties
      configMap:
        name: sdc-properties
        
And add a Volume Mount to the SDC container, to overwrite the <code>sdc.properties</code> file:

    volumeMounts:
    - name: sdc-properties
      mountPath: /etc/sdc/sdc.properties
      subPath: sdc.properties

Create and start a Control Hub deployment using those settings and confirm the expected values are set in sdc.properties.

For example:

    $ kubectl get pods | grep sdc
    sdc-59d44698b9-kvd7n             1/1     Running   0          2m54s
    
    $ kubectl exec -it sdc-59d44698b9-kvd7n -- sh -c 'grep 'production.maxBatchSize' /etc/sdc/sdc.properties'
    production.maxBatchSize=20000

See [sdc.yaml](sdc.yaml) for an example manifest.


