Interaction with StreamSets Control Hub pipelines
=================================================

This set contains tutorials for [StreamSets Control Hub pipelines](https://streamsets.com/documentation/controlhub/latest/help/datacollector/UserGuide/Pipeline_Design/What_isa_Pipeline.html). 

A pipeline describes the flow of data from the origin system to destination systems and defines how to transform the data along the way.

### Prerequisites
Before starting on any of the tutorials in this set, make sure to complete [Prerequisites for the pipelines tutorial](preparation-for-tutorial/README.md). 

### Tutorials for Pipelines

1. [Common pipeline methods](common-pipeline-methods/README.md) - Common operations for [StreamSets Control Hub pipelines](https://streamsets.com/documentation/controlhub/latest/help/datacollector/UserGuide/Pipeline_Design/What_isa_Pipeline.html) like update, duplicate, import, export.

1. [Loop over pipelines and stages and make an edit to stages](edit-pipelines-and-stages/README.md) - When there are many pipelines and stages that need an update, SDK for Python makes it easy to update them with just a few lines of code.

1. [Create CI CD pipeline used in demo](create-ci-cd-demo-pipeline/README.md) - This covers the steps to create CI CD pipeline as used in the [SCH CI CD demo](https://github.com/dimaspivak/sch_ci_cd_poc). The steps include how to add stages like JDBC, some processors and Kineticsearch; and how to set stage configurations.  Also shows, the use of runtime parameters.

### Conclusion

To get to know more details about SDK for Python, check the [SDK documentation](https://streamsets.com/documentation/sdk/latest/index.html).

If you don't have access to SCH, sign up for 30-day free trial by visiting https://streamsets.com/products/sch/control-hub-trial.