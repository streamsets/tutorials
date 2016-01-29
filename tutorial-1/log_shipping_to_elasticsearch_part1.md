
## Part 1 - Basic Log Preparation


### Creating a Pipeline
* Launch the Data Collector console and create a new pipeline.

* Note: *If you'd like, feel free to download a previously created [pipeline](pipelines/Directory_to_ElasticSearch_Tutorial_Part_1.json) that has been configured with the contents of this tutorial. In the Data Collector Home page, select Import Pipeline to begin.*
<img style="width:80%;" src="img/import_pipeline.png">

 #### Defining the Source
* Drag the Directory origin stage into your canvas.

* Go to the Configuration properties below and select the *Files* tab.

<img style="width:100%;" src="img/directory_config.png">

* Configure the following properties:

  * **Data Format** - Log
  * **Files Directory** - the absolute file path to the directory containing the sample .log.gz files
  * **File Name Pattern** - `*.gz`
  *this will pick up all .gz files in this folder, you can use any wildcard to narrow down your selection*
  * **Files Compression** - Compressed File

* In the *Post Processing* tab, make sure **File Post Processing** is set to None.

*Note: This drop down also lets you delete source files after they have been processed. You may want to use this in your production systems once you have verified your pipelines are configured correctly.*
<img style="width:100%;" src="img/directory_config_postproc.png">

* In the **Log** Tab set the **Log Format** option to Combined Log Format.

*Note: Data Collector already knows the format of the Combined Log Format and a few other log types, and has built in regex patterns to decode them. If you are working with custom log formats, choose either Regular Expression or Grok Pattern from the menu and define your own format. *
<img style="width:100%;" src="img/directory_config_log.png">

   #### Defining the 'geo' field
* Drag and drop an Expression Evaluator processor into the canvas.

* In its Configuration properties, select the *Expressions* tab.

* Under Field Expressions, add an output field called **/geo** and set the field expression to `${emptyMap()}`.
*This creates a Map data structure to hold the value of the geo object that we will populate later.*

<img style="width:100%;" src="img/expression_eval.png">


    #### Converting Fields
 By default Data Collector will read the fields in the log file as string values. This works for most fields, however we know that web server logs contain numeric values for Response Code, Bytes Transferred and a Date Time stamp. Let's convert these fields to the right data types.

 * Drag and drop a Field Converter processor into the pipeline.

 * Go to the Configuration properties and select the Conversions tab.
<img style="width:100%;" src="img/field_converter.png">
 * In the **Fields to Convert** text box, type `/bytes` and set **Convert to Type** to LONG.

 * Click the `+` icon to add another conversion.

 * In the new row, set **Fields to Convert** to `/response` and set **Convert to Type** to INTEGER.

 * Click the `+` icon to add another conversion.

 * In the new row, set **Fields to Convert** to `/timestamp` and set **Convert to Type** to DATETIME. Set **Date Format** to Other and in the **Other Date Format** text box type `dd/MMM/y:H:m:s Z`.
 *You can use [Java DateTime format specifiers](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) to change the format to suit your needs.*
<img style="width:100%;" src="img/field_converter_timestamp.png">

  #### Performing a GeoIP Lookup
  * Download a copy of the MaxMind free [GeoIP2 Lite City Database](http://geolite.maxmind.com/download/geoip/database/GeoLite2-City.mmdb.gz). Move the downloaded file to the StreamSets Resources folder and unzip it there.

  * Back in the Data Collector console, drag and drop the GeoIP processor into the canvas.

  * In its Configuration properties, select the *Geolocations* tab.
<img style="width:100%;" src="img/geo_ip.png">
  * In the GeoIP2 Database File text box, type 'GeoLite2-City.mmdb'.

  * Under Input Field Name, type `/clientip`, set its corresponding Output Field Name to `/city`, and set GeoIP2 Field to `CITY_NAME`.

  * Hit `+` to add another field, set Input Field Name to `/clientip`, set Output Field Name to `/lat`, and set GeoIP2 Field to `LATITUDE`.

  * Hit `+` again to add the last field, set Input Field Name to `/clientip`, set Output Field Name to `/lon`, and set GeoIP2 Field to `LONGITUDE`

  #### Setting up Elasticsearch

  * Finally, to configure a destination, drag and drop an Elasticsearch destination to the canvas.

  * In its Configuration properties, select the General tab. In the menu for Stage Library, select the version of Elasticsearch you are running.

<img style="width:100%;" src="img/elastic_config.png">

  * Go to the Elasticsearch tab, and in the Cluster Name text box enter the name of your cluster as specified in elasticsearch.yml.

  * In the Cluster URI field, specify the host:port where your Elasticsearch service is running.

  * In Index and Mapping text boxes write `logs`. This is the index and mapping that we set up earlier in this tutorial.

* Finally before we do anything with the pipeline, click on any blank spot on the canvas. In the pipeline configuration properties, click the Error Records tab. For the Error Records property, select 'Discard (Library:Basic)'. This effectively tells the pipeline to discard any erroneous data.
In a real production system, you can choose to send error records to a number of different systems.
<img style="width:100%;" src="img/discard_errors.png">
### Preview the Pipeline
  After you set up the pipeline, you can hit the Preview icon to examine the flow of a small subset of the data.

  Preview mode lets you interactively debug your stage configurations.

  #### Let's Ship Some Logs
  * Once the pipeline has been set up, hit the Start icon to execute the pipeline.

  * At this point, Data Collector should start reading off the origin directory and sending data into Elasticsearch.
  <img style="width:100%;" src="img/running_pipeline.png">

  * You can fire up a [Kibana Dashboard](kibana/ApacheWebLog.json) to view the results of the import into Elasticsearch
  <img style="width:100%;" src="img/part1_kibana_dashboard.png">
  *Notice that the Browser Type graph doesn't show up in Kibana, we'll take care of that next in Part 2.*

## What Next?
* In [Part 2](log_shipping_to_elasticsearch_part2.md) of this tutorial, we will see how to write custom Python code to enhance our log data. We will also set up metric alerts as we prepare the pipeline for production use.
