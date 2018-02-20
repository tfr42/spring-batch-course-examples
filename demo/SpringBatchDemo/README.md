# How to run the Spring Batch Demo application

To start the application run the maven goal:

	mvn exec:java -Dexec.mainClass=org.springframework.batch.core.launch.support.CommandLineJobRunner -Dexec.args="helloWorldJob.xml simpleJob" -Dspring.profiles.active=inmemory