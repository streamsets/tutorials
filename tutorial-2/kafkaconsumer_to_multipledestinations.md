## Part 2 - Reading with a Kafka Consumer

In this part of the tutorial we set up a pipeline that drains data from a Kafka topic, makes a couple of transformations and writes to multiple destinations.

* Note: *If you'd like, you can download a previously created [pipeline](pipelines/KafkaConsumer_to_MultipleDestinations.json) that has been configured with the contents of this tutorial.*

<img style="width:100%;" src="img/kafka_consumer_pipeline.png">

You may remember the data we are reading simulates credit card information and contains the card number, as follows:
```json
{
  "transaction_date":"dd/mm/YYYY",
  "card_number":"0000-0000-0000-0000",
  "card_expiry_date":"mm/YYYY",
  "card_security_code":"0000",
  "purchase_amount":"$00.00",
  "description":"transaction description of the purchase"
}
```
We don't want to store credit card information in any of our data stores so this is a perfect opportunity to sanitize the data before it gets there. We'll use a few built in processor stages to mask the card numbers so what makes it through are just the last 4 digits.

#### Defining the Origin
* Drag the Kafka Consumer origin stage into your canvas.

* In the Configuration settings, click the General tab. For Stage Library, select the version of Kafka that matches your environment.

<img style="width:100%;" src="img/kafka_consumer_config.png">

* In the Kafka tab set the Data Format to SDC Record. You may remember from Part 1 of this tutorial we sent data to Kafka in this format, so we want to make sure we decode the incoming data appropriately.

* Set the Broker URI, Zookeeper URI and topic name to match the settings in your environment.

#### Field Converter
* It so happens that the card number field is defined as an integer in Avro. We will want to convert this to a string value. So type "/card_number" in the Fields to Convert text box and set Convert to Type to String.
<img style="width:100%;" src="img/field_converter_config.png">

#### Jython Evaluator
* In this stage, we'll use a small piece of python code to look at the first few digits of the card number and figure out what type of card it is. We'll add that card type to a new field called "credit_card_type."

Go to the Jython tab of the Jython Evaluator and enter the following piece of code:

```python

for record in records:
  try:
    cc = record.value['card_number']
    if cc == '':
      err.write(record, "Credit Card Number was null")
      continue

    cc_type = ''
    if cc.startswith('4'):
      cc_type = 'Visa'
    elif cc.startswith(('51','52','53','54','55')):
      cc_type = 'MasterCard'
    elif cc.startswith(('34','37')):
      cc_type = 'AMEX'
    elif cc.startswith(('300','301','302','303','304','305','36','38')):
      cc_type = 'Diners Club'
    elif cc.startswith(('6011','65')):
      cc_type = 'Discover'
    elif cc.startswith(('2131','1800','35')):
      cc_type = 'JCB'
    else:
      cc_type = 'Other'

    record.value['credit_card_type'] = cc_type
    out.write(record)

  except Exception as e:
    # Send record to error
    err.write(record, str(e))

```

#### Field Masker
* The last step of the process is to mask the card number so that the last 4 digits of the card is all that makes it to the data stores.

<img style="width:100%;" src="img/field_masker_config.png">

* In the Field Masker properties, click the Mask tab. In the Fields To Mask property, type "/card_number" and set the mask type to Custom. In this mode, you can use '#' to show characters and any other character to use as a mask. For example, to mask all but the last 4 digits of the following credit card number: "0123 4567 8911 0123".
 
 You can use the following mask:"---- ---- ---- ####". 

This changes the value to "---- ---- ---- 0123".

#### Destinations
In this particular example we will write the results to 2 destinations: Elasticsearch and an Amazon S3 bucket.

##### Setting up Elasticsearch

* Drag an Elasticsearch destination to the canvas.

* In its Configuration settings, select the General tab. For the Stage Library property, select the version of Elasticsearch you are running.

<img style="width:100%;" src="img/elastic_config.png">

* Go to the Elasticsearch tab and in the Cluster Name property enter the name of your cluster as specified in elasticsearch.yml.

* For Cluster URI, specify the host:port where your Elasticsearch service runs.

* In the Index and Mapping properties, specify the name of your index and mapping.

##### Writing to an Amazon S3 Bucket
A common use case is to back up data to S3. In this example, we'll convert the data back to Avro data format and store it there.

* Drag an Amazon S3 destination to the canvas.

* In its Configuration settings, on the Amazon S3 tab, enter in your Access Key ID and Secret Access Key, select the Region and enter the Bucket name and Folder you want to store the files in.

* Pick Avro in the Data Format menu.

<img style="width:100%;" src="img/s3_config1.png">

* In the Avro tab, you need to specify the schema that you want encoded. Type in:
```json
{"namespace" : "cctest.avro",
 "type": "record",
 "name": "CCTest",
 "doc": "Test Credit Card Transactions",
 "fields": [
            {"name": "transaction_date", "type": "string"},
            {"name": "card_number", "type": "string"},
            {"name": "card_expiry_date", "type": "string"},
            {"name": "card_security_code", "type": "string"},
            {"name": "purchase_amount", "type": "string"},
            {"name": "description", "type": "string"}
           ]
}
```

<img style="width:100%;" src="img/s3_config2.png">


* To save space on the S3 buckets, let's compress the data as it's written. Choose BZip2 as the Avro Compression Codec.

#### Execute the Pipeline
* Hit Start and the pipeline should start draining Kafka messages and writing them to Elasticsearch and Amazon S3.
