# StreamSets Data Collector Tutorials

The following tutorials demonstrate some StreamSets [Data Collector](http://github.com/streamsets/datacollector/) features. Clone this repository to your machine to follow along and get familiar with using Data Collector.

## Basic Tutorials

- [Log Shipping to Elasticsearch](tutorial-1/readme.md) - Read weblog files from a local filesystem directory, decorate some of the fields (e.g. GeoIP Lookup), and write them to Elasticsearch.

- [Simple Kafka Enablement using StreamSets Data Collector](tutorial-2/readme.md)

- [What’s the Biggest Lot in the City of San Francisco?](tutorial-3/readme.md) - Read city lot data from JSON, calculate lot areas in JavaScript, and write them to Hive.

- [Ingesting Local Data into Azure Data Lake Store](tutorial-adls-destination/readme.md) - Read records from a local CSV-formatted file, mask out PII (credit card numbers) and send them to a JSON-formatted file in Azure Data Lake Store.

## Writing Custom Pipeline Stages

- [Creating a Custom StreamSets Origin](tutorial-origin/readme.md) - Build a simple custom origin that reads a Git repository's commit log and produces the corresponding records.

- [Creating a Custom Multithreaded StreamSets Origin](tutorial-origin/readme.md) - A more advanced tutorial focusing on building an origin that supports parallel execution, so the pipeline can run in multiple threads.

- [Creating a Custom StreamSets Processor](tutorial-processor/readme.md) - Build a simple custom processor that reads metadata tags from image files and writes them to the records as fields.

- [Creating a Custom StreamSets Destination](tutorial-destination/readme.md) - Build a simple custom destination that writes batches of records to a webhook.

## Advanced Features

- [Ingesting Drifting Data into Hive and Impala](tutorial-hivedrift/readme.md) - Build a pipeline that handles schema changes in MySQL, creating and altering Hive tables accordingly.

- [Creating a StreamSets Spark Transformer in Java](tutorial-spark-transformer/readme.md) - Build a simple Java Spark Transformer that computes a credit card's issuing network from its number.

- [Creating a StreamSets Spark Transformer in Scala](tutorial-spark-transformer-scala/readme.md) - Build a simple Scala Spark Transformer that computes a credit card's issuing network from its number.

The Data Collector documentation also includes an [extended tutorial](https://streamsets.com/documentation/datacollector/latest/help/#Tutorial/Overview.html) that walks through basic Data Collector functionality, including creating, previewing and running a pipeline, and creating alerts.

# License

StreamSets Data Collector and its tutorials are built on open source technologies; the tutorials and accompanying code are licensed with the [Apache License 2.0](LICENSE.txt).

# Contributing Tutorials

We welcome contributors! Please check out our [guidelines](CONTRIBUTING.md) to get started.