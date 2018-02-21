# How to run the Spring Batch Demo application

First build the application with:
    
    mvn clean install

To start the application run the maven goal:

	mvn exec:java -Dexec.mainClass=org.springframework.batch.core.launch.support.CommandLineJobRunner -Dexec.args="helloWorldJob.xml simpleJob" -Dspring.profiles.active=inmemory
	
CommandLineJobRunner arguments:

1. JobPath	- The location of the XML file (required).	
2. JobName	- The name of the job to be run (required).
3. JobParameters - All arguments after JobName are considered to be parameters and must be in the format of 'name=value'.


# Further reading

https://docs.spring.io/spring-batch/trunk/reference/html/configureJob.html