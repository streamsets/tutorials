# Simple Kafka Enablement using StreamSets Data Collector

Creating custom Kafka producers and consumers is often a tedious process that requires manual coding. In this tutorial we'll see how to use the StreamSets Data Collector to create data ingest pipelines to write to a Kafka Producer, and read from a Kafka Consumer with no handwritten code.

<img style="width:100%;" src="img/our_setup.png">


## Goals
The goal of this tutorial is read AVRO files from a file system directory and write them to a Kafka topic using the StreamSets Kafka Producer; we'll then use a second pipeline configured with a Kafka Consumer to drain out of that topic, perform a set of transformations and send the data to two different destinations.

## Pre-requisites
* A working instance of StreamSets Data Collector
* A working Kafka 0.9 (or older) instance
* A copy of this tutorials directory containing the [sample data](../sample_data), [pipeline 1](pipelines/Directory_to_KafkaProducer_Part_1.json) and, [pipeline 2] (pipelines/KafkaConsumer_to_MultipleDestinations_Part_1.json)


## Our Setup
The tutorial's [sample data directory](../sample_data) contains a set of '.avro' files that contain simulated credit card transactions in JSON format.

```json
{
  "transaction_date":"dd/mm/YYYY hh:mm:ss Z",
  "card_provider":"VISA",
  "card_number":"0000-0000-0000-0000",
  "card_expiry_date":"mm/YYYY",
  "card_security_code":"0000",
  "purchase_amount":"$00.00",
  "description":"transaction description of the purchase"
}
```
We create this fake data and serialize the files in Avro format using a [Fake Avro Data Generator Script](github.com/kiritbasu/Fake-Avro-Data-Generator)

## Let's Get Started
* Part 1 - [Publishing to a Kafka Producer](directory_to_kafkaproducer.md)
* Part 2 - [Reading from a Kafka Consumer](kafka_consumer_to_multipledestinations.md)
