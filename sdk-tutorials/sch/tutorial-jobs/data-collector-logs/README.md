View logs for the data collector where a job was run
====================================================

This tutorial covers how to get the DataCollector where a job is running and then see its logs.  

### Prerequisites
Make sure to complete [Prerequisites for the jobs tutorial](preparation-for-tutorial). 

### Tutorial environment details
While creating this tutorial following was used:
* Python 3.6
* StreamSets for SDK 3.8.0
* All StreamSets Data Collector with version 3.17.0

### Outline
In [Prerequisites for the jobs tutorial](preparation-for-tutorial), one job was created with name 'Job for Kirti-HelloWorld'. 
This tutorial shows the following:
1. Start the job and capture the data_collector where job was started
1. See logs for that data_collector
1. See logs for that data_collector for the particular pipeline that is tied with the job

### Workflow
On a terminal, type the following command to open a Python 3 interpreter.

```bash
$ python3
Python 3.6.6 (v3.6.6:4cf1f54eb7, Jun 26 2018, 19:50:54)
[GCC 4.2.1 Compatible Apple LLVM 6.0 (clang-600.0.57)] on darwin
Type "help", "copyright", "credits" or "license" for more information.
>>>
```

### Step 1 &mdash; Connect to StreamSets Control Hub instance

Let’s assume the StreamSets Control Hub is running at http://sch.streamsets.com 
Create an object called control_hub which is connected to the above. 

```python
from streamsets.sdk import ControlHub

# Replace the argument values according to your setup
control_hub = ControlHub(server_url='http://sch.streamsets.com',
                         username='user@organization1',
                         password='password')
```
 
### Step 2 &mdash; Start the job
Now let’s start the job.

 ```python
import time
# Select the job using name
job = control_hub.jobs.get(job_name='Job for Kirti-HelloWorld')
control_hub.start_job(job)
time.sleep(60)  # Let it run for a minute
  ```

### Step 3 &mdash; Get the data collector where job is started
Let's use Job status for that:

```python
job.refresh()  # Make sure to run this so that Job status is updated
job_status = job.status
# The id for the data_collector that was used to execute job
sdc_ids = job_status.sdc_ids
print('sdc_ids = ', sdc_ids)
```
Above code produces a sample output like following:
```bash
<JobStatus (status=ACTIVE, color=GREEN)>
sdc_ids =  ['380e3f59-d74e-11ea-b07f-adf940e256e9']
```

### Step 4 &mdash; Get the data collector log

```python
sdc_id = sdc_ids[0]  # Get the first ID
data_collector = control_hub.data_collectors.get(id=sdc_id)
sdc_log = data_collector.instance.get_logs()
print(sdc_log)
```
Above code produces a sample output like following (cut for brevity):
```bash
2020-08-08 21:53:45,737 [user:] [pipeline:] [runner:] [thread:pool-8-thread-1] INFO AntennaDoctorStorage - Downloading updates from: https://antenna.streamsets.com/datacollector/1/ None
2020-08-08 21:53:45,902 [user:] [pipeline:] [runner:] [thread:pool-8-thread-1] INFO AntennaDoctorStorage - No new changes None
2020-08-09 00:01:16,856 [user:] [pipeline:] [runner:] [thread:webserver-5006] ERROR AbstractSSOService - Exception while doing remote validation for token 'TOKEN:4501be23-66ec-4c...' component '-': com.streamsets.lib.security.http.ForbiddenException: {ISSUES=[{code=DPM_02, message=User validation failed}]} None
2020-08-09 09:53:45,737 [user:] [pipeline:] [runner:] [thread:pool-8-thread-1] INFO AntennaDoctorStorage - Downloading updates from: https://antenna.streamsets.com/datacollector/1/ None
2020-08-09 09:53:45,917 [user:] [pipeline:] [runner:] [thread:pool-8-thread-1] INFO AntennaDoctorStorage - No new changes None
2020-08-09 14:28:46,928 [user:*?] [pipeline:-] [runner:] [thread:eventHandlerExecutor-pool-5-thread-34] INFO RemoteEventHandlerTask - Handling SAVE_PIPELINE event: 'Server Event id: dcfab7c1-1fcc-40a3-be9b-656ac6b494df:engproductivity, type: SAVE_PIPELINE, isRequiresAck: true, isAckEvent: false, from: jobrunner-app'  None
2020-08-09 14:28:47,031 [user:*?] [pipeline:-] [runner:] [thread:eventHandlerExecutor-pool-5-thread-34] INFO RemoteDataCollector - Offset for remote pipeline 'Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity:0' is com.streamsets.datacollector.runner.production.SourceOffset@7304d563 None
2020-08-09 14:28:47,034 [user:] [pipeline:] [runner:] [thread:eventHandlerExecutor-pool-5-thread-34] INFO RemoteEventHandlerTask - Handling START_PIPELINE event: 'Server Event id: 10f27276-b1d1-4aa2-801f-a7c1493e54c3:engproductivity, type: START_PIPELINE, isRequiresAck: true, isAckEvent: false, from: jobrunner-app'  None
2020-08-09 14:28:47,034 [user:kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity] [runner:] [thread:eventHandlerExecutor-pool-5-thread-34] INFO StandaloneAndClusterRunnerProviderImpl - Pipeline execution mode is: STANDALONE  None
2020-08-09 14:28:47,035 [user:kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity] [runner:] [thread:eventHandlerExecutor-pool-5-thread-34] INFO StandaloneRunner - Preparing to start pipeline 'Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity::0' None
2020-08-09 14:28:47,063 [user:*kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity] [runner:] [thread:runner-pool-1-thread-20] INFO StandaloneRunner - Starting pipeline Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity 0 None
2020-08-09 14:28:47,121 [user:*kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity] [runner:] [thread:runner-pool-1-thread-20] INFO ProductionPipelineRunner - Adding error listeners0 None
2020-08-09 14:28:47,164 [user:*kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity] [runner:] [thread:ProductionPipelineRunnable-Kirti-HelloWorld__27f317ff-0f94-4668-adce-8264528f1c7f__engproductivity-Kirti-HelloWorld] INFO Pipeline - Processing lifecycle start event with stage None
```

### Step 5 &mdash; Get the data collector log only for the particular pipeline that is tied with the job

```python
sdc_id = sdc_ids[0]  # Get the first ID
data_collector = control_hub.data_collectors.get(id=sdc_id)
pipeline = control_hub.pipelines.get(pipeline_id=job.pipeline_id)
sdc_log = data_collector.instance.get_logs(pipeline=pipeline)
print(sdc_log)
```
Above code produces a sample output like following (cut for brevity):
```bash
<Pipeline (id=Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity, title=Kirti-HelloWorld)>
2020-08-09 20:10:32,464 [user:kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity] [runner:] [thread:eventHandlerExecutor-pool-5-thread-48] INFO StandaloneAndClusterRunnerProviderImpl - Pipeline execution mode is: STANDALONE  None
2020-08-09 20:10:32,464 [user:kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity] [runner:] [thread:eventHandlerExecutor-pool-5-thread-48] INFO StandaloneRunner - Preparing to start pipeline 'Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity::0' None
2020-08-09 20:10:32,494 [user:*kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity] [runner:] [thread:runner-pool-1-thread-6] INFO StandaloneRunner - Starting pipeline Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity 0 None
2020-08-09 20:10:32,550 [user:*kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity] [runner:] [thread:runner-pool-1-thread-6] INFO ProductionPipelineRunner - Adding error listeners0 None
2020-08-09 20:10:32,599 [user:*kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity] [runner:] [thread:ProductionPipelineRunnable-Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity-Kirti-HelloWorld] INFO Pipeline - Processing lifecycle start event with stage None
2020-08-09 20:12:43,262 [user:*kirti@engproductivity] [pipeline:Kirti-HelloWorld/Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity] [runner:] [thread:ProductionPipelineRunnable-Kirti-HelloWorld__be034b54-10cd-41bf-80bd-ebf5f1acca0c__engproductivity-Kirti-HelloWorld] INFO RandomDataGeneratorSource - Shutting down executor service None
```

### Follow-up
To get to know more details about SDK for Python, check the [SDK documentation](https://streamsets.com/documentation/sdk/latest/index.html).

If you encounter any problems with this tutorial, please [file an issue in the tutorials project](https://github.com/streamsets/tutorials/issues/new).
 
