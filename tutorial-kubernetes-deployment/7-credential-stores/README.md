### Loading <code>credential-stores.properties</code> from a Secret

This example shows how to load a <code>credential-stores.properties</code> file from a Secret.  This technique is useful if you have different credential stores in different environments (for example, Dev, QA, Prod) and want each environment's SDCs to automatically load the appropriate settings.  

Start by creating a <code>credential-stores.properties</code> file.  For example, a <code>credential-stores.properties</code> file used for Azure Key Vault might look like this:

    credentialStores=azure
    credentialStore.azure.def=streamsets-datacollector-azure-keyvault-credentialstore-lib::com_streamsets_datacollector_credential_azure_keyvault_AzureKeyVaultCredentialStore
    credentialStore.azure.config.credential.refresh.millis=30000
    credentialStore.azure.config.credential.retry.millis=15000
    credentialStore.azure.config.vault.url=https://mykeyvault.vault.azure.net/
    credentialStore.azure.config.client.id=[redacted]
    credentialStore.azure.config.client.key=[redacted]
    
Store the <code>credential-stores.properties</code> file in a Secret; I'll name my secret <code>azure-key-vault-credential-store</code>:

    $ kubectl create secret generic azure-key-vault-credential-store --from-file=credential-stores.properties 

In your SDC deployment manifest, create a Volume for the Secret:

    volumes:
    - name: azure-key-vault-credential-store
      secret:
        secretName: azure-key-vault-credential-store
 
And then create a Volume Mount that overwrites the default <code>credential-stores.properties</code> file:

    volumeMounts:
    - name: azure-key-vault-credential-store
      mountPath: /etc/sdc/credential-stores.properties
      subPath: credential-stores.properties
      
See [sdc.yaml](sdc.yaml) for an example manifest.

Make sure to load the Azure Key Vault Credentials Store stage library in your deployment to order to run this example.
         

