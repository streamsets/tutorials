### A note about Environment Variables in SDC deployment manifests
Environment variables specified in SDC Deployment manifests that have the prefix <code>SDC_CONF_</code>, like <code>SDC_CONF_SDC_BASE_HTTP_URL</code>, can be used to dynamically set properties in the deployed SDC's <code>sdc.properties</code> file. 

These environment variables are mapped to SDC properties by trimming the <code>SDC_CONF_</code> prefix, lowercasing the name and replacing "_" with ".".  For example, the value set in the environment variable <code>SDC_CONF_SDC_BASE_HTTP_URL</code> will be set in <code>sdc.properties</code> as the property <code>sdc.base.http.url</code>.

However, due to the current behavior of the SDC image's <code>docker-entrypoint.sh</code> script, this technique is not able to set mixed-case properties in <code>sdc.properties</code> like <code>production.maxBatchSize</code>. This limitation may be lifted in the near future.

In the meantime, if mixed-case SDC properties need to be set, they can either be set in an <code>sdc.properties</code> file packaged in a custom SDC image, as in the [Custom Docker Image example](2-custom-docker-image), or loaded from a configmap as shown in  the examples [here](5-sdc-properties-configmap-1) and [here](6-sdc-properties-configmap-2).

It's also worth noting that values for environment variables with the prefix <code>SDC_CONF_</code> are written to the <code>sdc.properties</code> file by the SDC container's <code>docker-entrypoint.sh</code> script, which forces the SDC container to have read/write access to the <code>sdc.properties</code> file, which may not be the case if <code>sdc.properties</code> is mounted with read-only access.

Best practice for now is to mount <code>sdc.properties</code> from a configmap and to avoid using <code>SDC_CONF\__</code> environment variables.