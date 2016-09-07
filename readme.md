# StreamSets Data Collector Tutorials

The following tutorials demonstrate some StreamSets [Data Collector](http://github.com/streamsets/datacollector/) features. Clone this repository to your machine to follow along and get familiar with using Data Collector.

- [Log Shipping to Elasticsearch](tutorial-1/readme.md) - Read weblog files from a local filesystem directory, decorate some of the fields (e.g. GeoIP Lookup), and write them to Elasticsearch.

- [Simple Kafka Enablement using StreamSets Data Collector](tutorial-2/readme.md)

- [What’s the Biggest Lot in the City of San Francisco?](tutorial-3/readme.md) - Read city lot data from JSON, calculate lot areas in JavaScript, and write them to Hive.

- [Creating a Custom StreamSets Destination](tutorial-destination/readme.md) - Build a simple custom destination that writes batches of records to a webhook.

- [Ingesting Drifting Data into Hive and Impala](tutorial-hivedrift/readme.md) - Build a pipeline that handles schema changes in MySQL, creating and altering Hive tables accordingly.

The Data Collector documentation also includes an [extended tutorial](https://streamsets.com/documentation/datacollector/latest/help/#Tutorial/Overview.html) that walks through basic Data Collector functionality, including creating, previewing and running a pipeline, and creating alerts.
