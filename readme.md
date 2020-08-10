# StreamSets Data Collector Tutorials

The following tutorials demonstrate some StreamSets [Data Collector](http://github.com/streamsets/datacollector/) features. Clone this repository to your machine to follow along and get familiar with using Data Collector.

## Basic Tutorials

- [Log Shipping to Elasticsearch](tutorial-1/readme.md) - Read weblog files from a local filesystem directory, decorate some of the fields (e.g. GeoIP Lookup), and write them to Elasticsearch.

- [Simple Kafka Enablement using StreamSets Data Collector](tutorial-2/readme.md)

- [What’s the Biggest Lot in the City of San Francisco?](tutorial-3/readme.md) - Read city lot data from JSON, calculate lot areas in JavaScript, and write them to Hive.

- [Ingesting Local Data into Azure Data Lake Store](tutorial-adls-destination/readme.md) - Read records from a local CSV-formatted file, mask out PII (credit card numbers) and send them to a JSON-formatted file in Azure Data Lake Store.

- [Working with StreamSets Data Collector and Microsoft Azure](working-with-azure/readme.md) - Integrate [Azure Blob Storage](https://azure.microsoft.com/en-us/services/storage/blobs/), [Apache Kafka on HDInsight](https://docs.microsoft.com/en-us/azure/hdinsight/), [Azure SQL Data Warehouse](https://azure.microsoft.com/en-us/services/sql-data-warehouse/) and Apache Hive backed by Azure Blob Storage.

## Writing Custom Pipeline Stages

- [Creating a Custom StreamSets Origin](tutorial-origin/readme.md) - Build a simple custom origin that reads a Git repository's commit log and produces the corresponding records.

- [Creating a Custom Multithreaded StreamSets Origin](tutorial-origin/readme.md) - A more advanced tutorial focusing on building an origin that supports parallel execution, so the pipeline can run in multiple threads.

- [Creating a Custom StreamSets Processor](tutorial-processor/readme.md) - Build a simple custom processor that reads metadata tags from image files and writes them to the records as fields.

- [Creating a Custom StreamSets Destination](tutorial-destination/readme.md) - Build a simple custom destination that writes batches of records to a webhook.

## Advanced Features

- [Ingesting Drifting Data into Hive and Impala](tutorial-hivedrift/readme.md) - Build a pipeline that handles schema changes in MySQL, creating and altering Hive tables accordingly.

- [Creating a StreamSets Spark Transformer in Java](tutorial-spark-transformer/readme.md) - Build a simple Java Spark Transformer that computes a credit card's issuing network from its number.

- [Creating a StreamSets Spark Transformer in Scala](tutorial-spark-transformer-scala/readme.md) - Build a simple Scala Spark Transformer that computes a credit card's issuing network from its number.

- [Creating a CRUD Microservice Pipeline](tutorial-crud-microservice/readme.md) - Build a microservice pipeline to implement a RESTful web service that reads from and writes to a database via JDBC.

The Data Collector documentation also includes an [extended tutorial](https://streamsets.com/documentation/datacollector/latest/help/#Tutorial/Overview.html) that walks through basic Data Collector functionality, including creating, previewing and running a pipeline, and creating alerts.


## Control Hub

- [Creating Custom Data Protector Procedure](tutorial-custom-dataprotector-procedure/README.md) - Create, build and deploy your own custom data protector procedure that you can use as protection method to apply to record fields.

## Transformer

- [Creating a Custom Processor for StreamSets Transformer](https://github.com/metadaddy/transformer-sample-processor/blob/master/README.md) - Create a simple custom processor, using Java and Scala, that will compute the type of a credit card from its number, and configure Transformer to use it.

- [Creating a Custom Scala Project For StreamSets Transformer](https://github.com/iamontheinet/StreamSets/tree/master/Transformer_Custom_Scala_Project) - This tutorial explains how to create a custom Scala project and import the compiled jar into StreamSets Transformer.

## Kubernetes-based Deployment

- [Kubernetes-based Deployment](tutorial-kubernetes-deployment/README.md) - Example configurations for Kubernetes-based deployments of StreamSets Data Collector

## StreamSets SDK for Python
#### Control Hub 
-  [Getting started with StreamSets SDK for Python](sdk-tutorials/sch/tutorial-getting-started/README.md) - Design and publish a pipeline. Then create, start, and stop a job using [StreamSets SDK for Python](https://streamsets.com/documentation/sdk/latest/index.html). 


-  [Jobs related tutorials](sdk-tutorials/sch/tutorial-getting-started/README.md)
    - [Sample ways to fetch one or more jobs](sdk-tutorials/sch/tutorial-jobs/ways-to-fetch-jobs/README.md) - Sample ways to fetch one or more jobs.
    - [Start a job and monitor that specifc job](sdk-tutorials/sch/tutorial-jobs/start-monitor-a-specific-job/README.md) - Start a job and monitor that specific job using metrics and time series metrics.
    - [Update data_collector_labels for jobs](sdk-tutorials/sch/tutorial-jobs/update-data-collector-labels/README.md) - Move jobs from one data_collector label to another.
    - [Generate a report for a specific job](sdk-tutorials/sch/tutorial-jobs/generate-a-report/README.md) - Generate a report for a specific job and then; fetch and download it.
    - [See logs for a data-collector where a job is running](sdk-tutorials/sch/tutorial-jobs/data-collector-logs/README.md) - Get the DataCollector where a job is running and then see its logs.
   

# License

StreamSets Data Collector and its tutorials are built on open source technologies; the tutorials and accompanying code are licensed with the [Apache License 2.0](LICENSE.txt).

# Contributing Tutorials

We welcome contributors! Please check out our [guidelines](CONTRIBUTING.md) to get started.