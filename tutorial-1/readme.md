# Log shipping into ElasticSearch

In this three part tutorial, we will learn how to read apache web server logs and send them to ElasticSearch. Along the way we will transform the data and set up alerts and data rules to let us know if any bad data is encountered.

The Data Collector can read from and write to a large number of sources and destinations, but for this tutorial we will limit our scope to a File Directory Source and an ElasticSearch Destination.

## Goals
The goal of this tutorial is to gather apache log files and send them to ElasticSearch.

## Pre-requisites
* A working instance of StreamSets Data Collector
* Access to ElasticSearch and Kibana
* A copy of this tutorials directory containing the [sample data](../sample_data) and [pipeline](pipelines/Directory_to_ElasticSearch_Tutorial_Part_1.json)
* A copy of the MaxMind GeoLite2 free IP Geolocation Database. [GeoLite2 City](https://dev.maxmind.com/geoip/geoip2/geolite2/).

## Our Setup
The tutorials [sample data directory](../sample_data) contains a set of apache web server log files. The Data Collector can read many file formats but for this example we will use compressed logs (.log.gz) that simulates a system that generates log rotated files.

The log files contain standard Apache Combined Log Format Data.

` host rfc931 username date:time request statuscode bytes referrer user_agent `

*If you'd like to generate a larger volume of log files, you can use the [Fake Apache Log Generator](http://github.com/kiritbasu/Fake-Apache-Log-Generator) script*

### Setting up an index on ElasticSearch
We will need to setup an index with the right mapping before we can use ElasticSearch, here's how :
```
$ curl -XPUT 'http://localhost:9200/logs' -d '{
    "mappings": {
        "logs" : {
            "properties" : {
                "timestamp": {"type": "date"},
                "geo": {"type": "geo_point"},
                "city": {
                    "type": "string",
                "index": "not_analyzed"
                }
            }
        }
    }
}
```
This piece of code effectively creates in index called 'logs' and defines a few field types :
* *timestamp* - this is a date field
* *geo* - this is a geo_point field that has lat/lon attributes
* *city* - this is a string type that is not analyzed thus preventing elastic from truncating the data

## Lets get started
* [Part 1 - Basic Log preparation](./log_shipping_to_elasticsearch_part1.md)
* [Part 2 - Enhancing Log Data](log_shipping_to_elasticsearch_part2.md)
* [Part 3 - Preparing for production](log_shipping_to_elasticsearch_part3.md)
