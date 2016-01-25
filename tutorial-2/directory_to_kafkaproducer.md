## Part 1 - Publishing to a Kafka Producer


### Creating a Pipeline
* Open the DataCollector and create a new pipeline.

* Note: *If you'd like, feel free to download a previously created [pipeline](pipelines/Directory_to_KafkaProducer_Tutorial_Part_1.json) that has been configured with the contents of this tutorial.*

#### Defining the source
* Drag the 'Directory' origin stage into your canvas.

* Go to the Configuration Settings below and Select the *Files* tab

<img style="width:100%;" src="img/directory_setup.png">

* Enter the following settings :

 * **Data Format** - Avro
 * **Files Directory** - the absolute file path to the directory containing the sample .avro files
 * **File Name Pattern** - `cc*`
 *the ccdata file in the samples directory is a bzip2 compressed avro file, the data collector will automatically detect and decrypt it on the fly*
 * **Files Compression** - None


* In the *Post Processing* tab make sure **File Post Processing** is set to None.

*Note: This dropdown also lets you delete source files after they have been processed. You may want to use this in your production systems once you have verified your pipelines are configured correctly.*
<img style="width:100%;" src="img/directory_config_postproc.png">

* In the **Avro** Tab leave the defaults as is.

*Note: Avro already contains the schema that the Data Collector will pick up and decode on the fly. If you'd like to override the default schema, enter the custom schema in this tab*

#### Defining the Kafka Producer
* Drag and drop the 'Kafka Producer' to the canvas.

* Go to the 'General' Tab in its configuration and select the version of Kafka that matches your environment in the 'Stage Library' dropdown.

* Go to the 'Kafka' Tab and set the 'Broker URI' to point to your kafka broker. e.g.`<hostname/ip>:<port>` Set the 'Topic' to the name of your kafka topic. And the 'Data Format' to 'SDC Record'
<img style="width:100%;" src="img/kafka_producer_config.png">

*SDC Record is the internal data format that is highly optimized for use within the StreamSets Data Collector (SDC); Since we are going to be using a SDC on the other side to read from this Kafka Topic we can use 'SDC Record' since it knows how to decode the format. If you have a custom Kafka Consumer on the other side you may want to choose from one of the other Data Formats in this drop down and decode it accordingly.*

You can use the 'Kafka Configuration' section of this tab to enter any specific Kafka settings you would like; In a future tutorial we'll see how to configure TLS, SASL or Kerberos with Kafka.

You may choose to transform some of the data using any of the 'Processor Stages' before you send them over Kafka, however for this tutorial we will do the transformations on the receiving end.

That's it! Your pipeline is now ready to feed messages into Kafka.

#### Preview the Data
* Feel free to hit the 'Preview' icon to examine the data before executing the pipeline.

#### Execute the Pipeline
* Hit the run button and if your Kafka server is up and running the pipeline should start sending data over Kafka.

#### Where to go from here
* Part 2 - [Reading from a Kafka Consumer](kafkaconsumer_to_multipledestinations.md)
