## Kubernetes Deployment

This tutorial provides examples and guidance for deploying custom configurations of [StreamSets Data Collector](https://streamsets.com/products/dataops-platform/data-collector) on Kubernetes using [Control Hub](https://streamsets.com/products/dataops-platform/control-hub). Please see [the note about Environment Variables](NoteOnEnvVars.md).

### Prerequisites

* A Kubernetes Cluster

* A deployed [Provisioning Agent](https://streamsets.com/documentation/controlhub/latest/help/controlhub/UserGuide/DataCollectorsProvisioned/ProvisionSteps.html#concept_hjy_tft_1gb)

* A deployed [Ingress Controller](https://kubernetes.cn/docs/concepts/services-networking/ingress-controllers/) for the Ingress example
 

### Examples

1. [How to set Java Heap Size and other Java Options](1-java-opts)

1. [Baked-in Stage Libs and Configuration](2-custom-docker-image)

1. [Loading Stage Libs from a pre-populated Volume](3-volumes)

1. [Loading Stage Libs from a Persistent Volume](4-persistent-volumes)

1. [Loading <code>sdc.properties</code> from a ConfigMap](5-sdc-properties-configmap-1)

1. [Loading static and dynamic <code>sdc.properties</code> from separate ConfigMaps](6-sdc-properties-configmap-2)

1. [Loading <code>credential-stores.properties</code> from a Secret](7-credential-stores)

1. [Ingress](8-ingress)
