### Pre-requisites

In order to work through these tutorials, ensure you already have the following setup ready, otherwise follow the instructions described below:

1. Login to the [Azure Portal](https://portal.azure.com)

2. Create a [Virtual Network](https://docs.microsoft.com/en-us/azure/virtual-network/quick-create-portal#create-a-virtual-network) (vNet):
This vNet will allow us to enable communication between the clusters created in the next steps to communicate privately with each other.

3. Data storage: Create a [Blob Container](https://docs.microsoft.com/en-us/azure/hdinsight/hdinsight-hadoop-use-blob-storage#create-blob-containers)

4. Install [StreamSets Data Collector for HDInsight](https://docs.microsoft.com/en-us/azure/hdinsight/hdinsight-apps-install-streamsets)
Also check [StreamSets Documentation](https://streamsets.com/documentation/datacollector/latest/help/index.html#datacollector/UserGuide/Installation/CloudInstall.html#task_vnj_rl2_wdb) for more details on the installation process.

5. Create an [HDInsight Kafka cluster](https://docs.microsoft.com/en-us/azure/hdinsight/kafka/apache-kafka-get-started) in the same vNet as above. Use Azure Storage for the Kafka cluster. 

6. Configure StreamSets Data Collector
	a) Configure connection to HDInsight cluster by creating symlinks to the configuration files.
		# ssh into the SDC node using the SSH Endpoint of your cluster:
		ssh sshuser@<ssh endpoint>
		# Navigate to the StreamSets Resources Directory and create a directory to hold cluster configuration symlinks
		cd /var/lib/sdc-resources
		sudo mkdir hadoop-conf
		cd hadoop-conf
		# Symlink all *.xml files from /etc/hadoop/conf and hive-site.xml from /etc/hive/conf:
		sudo ln -s /etc/hadoop/conf/*.xml .
		sudo ln -s /etc/hive/conf/hive-site.xml .

![image alt text](img/sdc_ssh_login.png)

	b) Install SQL Server JDBC Driver:
		i)Depending on your SQL Server version, download the appropriate JDBC driver. E.g. for SQL Server 2017, download from [here](https://docs.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-2017)
		ii) Install the downloaded SQL Server JDBC driver using the method described [here](https://streamsets.com/documentation/datacollector/latest/help/index.html#datacollector/UserGuide/Configuration/ExternalLibs.html#concept_amy_pzs_gz)

7. Capture and note down the Kafka Broker URI and Zookeeper Configuration from Ambari:
![image alt text](img/Ambari_Kafka.png)