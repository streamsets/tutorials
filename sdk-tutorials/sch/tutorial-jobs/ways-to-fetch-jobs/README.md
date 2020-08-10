Some sample ways to fetch one or more jobs
==========================================

This tutorial shows few ways to fetch one or more jobs. 

### Prerequisites
Make sure to complete [Prerequisites for the jobs tutorial](preparation-for-tutorial). 

### Tutorial environment details
While creating this tutorial following was used:
* Python 3.6
* StreamSets for SDK 3.8.0
* All StreamSets Data Collector with version 3.17.0

 
### Workflow
On a terminal, type the following command to open a Python 3 interpreter.

```bash
$ python3
Python 3.6.6 (v3.6.6:4cf1f54eb7, Jun 26 2018, 19:50:54)
[GCC 4.2.1 Compatible Apple LLVM 6.0 (clang-600.0.57)] on darwin
Type "help", "copyright", "credits" or "license" for more information.
>>>
```

### Connect to StreamSets Control Hub instance

Let’s assume the StreamSets Control Hub is running at http://sch.streamsets.com 
Create an object called control_hub which is connected to the above. 

```python
from streamsets.sdk import ControlHub

# Replace the argument values according to your setup
control_hub = ControlHub(server_url='http://sch.streamsets.com',
                         username='user@organization1',
                         password='password')
```
 
### 1 &mdash; Get all jobs

If you have lot of jobs, this might be halting the system and so do not use this methid to choose jobs.
  
```python
print('All jobs')
all_jobs = control_hub.jobs
print(all_jobs)
```
Sample output is like following:
```
[<Job (job_id=995d8c2e-24e7-4d52-8fcf-8b9493e885fe:engproductivity, job_name=Job for Kirti-DevDataRawDS)>,
<Job (job_id=ec1709e6-036e-4f38-854c-6050e8b51c30:engproductivity, job_name=Job for Kirti-HelloWorld)>,
<Job (job_id=37ae360e-add4-485c-b7c3-d7abc054d151:engproductivity, job_name=Job for People Data to MySQL)>,
<Job (job_id=1d271393-a54b-41c6-965c-084af6a7abaf:engproductivity, job_name=Job for Trivial)>]
```

### 2 &mdash; Get a limited number of jobs and also specify sort order

```python
limited_num_of_jobs = control_hub.jobs.get_all(len=2, order='ASC')
print('\nlimited_num_of_jobs are as following: ', limited_num_of_jobs)
```
   
### 3 &mdash; Get a specific job using name

```python
first_job = control_hub.jobs.get(job_name='Job for Kirti-HelloWorld')
```
   
### 4 &mdash; Get a specific job using id

```python
job = control_hub.jobs.get(id=first_job.job_id)
print(job)
```

### 5 &mdash; Get a list of jobs specified by pipeline_commit_label

```python
jobs = control_hub.jobs.get_all(pipeline_commit_label='v1')
print(jobs)
```  

### 6 &mdash; Get a list of jobs specified by job_tag

```python
first_job_tags = first_job.job_tags
first_job_first_tag_id = first_job_tags[0]['id'] if first_job_tags else ''
if first_job_first_tag_id:
    jobs = control_hub.jobs.get_all(job_tag=first_job_first_tag_id)
    print(jobs)
```      

### Follow-up
To get to know more details about SDK for Python, check the [SDK documentation](https://streamsets.com/documentation/sdk/latest/index.html).

If you encounter any problems with this tutorial, please [file an issue in the tutorials project](https://github.com/streamsets/tutorials/issues/new).
 
