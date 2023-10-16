# Spring REST Demo for All Things Open 2023

Requirements:<br>  
JMeter 5.6.2: https://jmeter.apache.org/download_jmeter.cgi<br>  
Apache Tomcat 11: https://tomcat.apache.org/download-11.cgi<br>  
JDK 21: https://adoptium.net/temurin/releases/  or you can get it here: https://jdk.java.net/21/<br>  

Remember to modify useVirtualThreads (default is false) in Tomcat's server.xml on the 8080 port connector:

    <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               useVirtualThreads="false/true"
               redirectPort="8443" />

Always remember to set JAVA_HOME:
```
export JAVA_HOME=/home/rocky/jdk-21
```
A restart is required for this change to take affect.

**IMPORTANT NOTES**: 
Please change the datasource url in the application.yaml to the MySQL server you are connecting too.
Also, modify the ```THE_URL``` String in ```com.openlogic.rest``` to the httpd or NGINX server you want to retrieve a page from.

To build: ```mvn clean package```

Copy the ```spring-rest-apache-tomcat-demo.war``` in the target folder of the build to Apache Tomcat's ```webapps``` folder.
Monitor ```catalina.out``` for any application start up exceptions.

To run the JMeter script:

NOTE: You will need to change HTTPSampler.domain on LINE 33 of EACH .jmx script to the Apache Tomcat server you are stress testing under ```jmeterTestPlan/hashTree/hashTree/hashTree/HTTPSamplerProxy/stringProp```
1) Create a directory that will store your test results:
```
mkdir ~/results-files
mkdir -p ~/database-inserts/standard-threads
mkdir -p ~/database-inserts/virtual-threads
mkdir -p ~/page-retrievals/standard-threads
mkdir -p ~/page-retrievals/virtual-threads
mkdir -p ~/multiply-doubles/standard-threads
mkdir -p ~/multiply-doubles/virtual-threads
```

Run a database insertion test:<br>  
```
~/apache-jmeter-5.6.2/bin/jmeter -n -t ~/virtual-thread-test-db.jmx -l ~/results-files/results-"`date "+%Y.%m.%d-%H.%M.%S"`".file -e -o ~/database-inserts/standard-threads
```

Run a page retrieval test:<br>  
```
~/apache-jmeter-5.6.2/bin/jmeter -n -t ~/virtual-thread-test-nginx.jmx -l ~/results-files/results-"`date "+%Y.%m.%d-%H.%M.%S"`".file -e -o ~/page-retrieval/standard-threads
```

Run a multiply doubles test:
```
~/apache-jmeter-5.6.2/bin/jmeter -n -t ~/virtual-thread-test-multiply-doubles.jmx -l ~/results-files/results-"`date "+%Y.%m.%d-%H.%M.%S"`".file -e -o ~/multiply-doubles/standard-threads
```
