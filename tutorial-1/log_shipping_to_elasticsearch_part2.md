## Part 2 - Enhancing Log Data

Now that we've examined the basics of how use the Data Collector, let's see how to clean up and/or decorate the log data before posting it into ElasticSearch. We'll also look at some nifty features (Metric Alerts and Data Rules) within the Data Collector that sets up alerts for production mode.

### Before we begin
* Clean up ElasticSearch *delete any previous test data by running the following command.*
```bash
$ curl -XDELETE 'http://localhost:9200/logs'
```

* Recreate the Elastic Index
```bash
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

### Adding a Jython Evaluator
The log files contain a User Agent string that contains a lot of information about the browser, for the sake of this exercise we want to parse the UA string and only extract the name of the browser. Let's use the Python [user-agents](https://pypi.python.org/pypi/user-agents/0.2.0) package for this.
* Install user-agents to your computer.
```pip install pyyaml ua-parser user-agents```

* In your existing pipeline, click the connector between the GeoIP and ElasticSearch stage and select Jython Evaluator from the Add Processor dropdown.
<img style="width:100%;" src="img/add_jython.png">

* Add the following code snippet to the Jython Evaluator :
```Python
import sys
sys.path.append('/Library/Python/2.7/site-packages')
from user_agents import parse

for record in records:
  try:
    user_agent = parse(record.value['agent'])
    record.value['browser'] = user_agent.browser.family
    # Write record to procesor output
    out.write(record)

  except Exception as e:
    # Send record to error
    err.write(record, str(e))
```
