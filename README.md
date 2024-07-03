# design-philosophy
- initial impression to create two layered components
	- layer-1 : core structures, components intended to take time text, validate it, process it and give back the result as british-spoken-time
		- add extensibility to support other locales (due to time limit this could be future scope)
    - layer-2 : contains interfaces through which a user can access the core components of layer-1
		- client-1 : a restful service -  for simplicity this is deployed as springboot app 
			- http://localhost:8080/spoken-time?timeInput=10:10
			
### stack used to run tests
- jdk 17
- maven 3
- chrome browser 

### how to run this 
- git repo : https://github.com/Prashanth-T-R/spoken-time-tool
```bash
git clone git@github.com:Prashanth-T-R/spoken-time-tool.git
cd spoken-time-tool
### sanity test for maven  tested with Apache Maven 3.9.8
mvn -v
### sanity test for java  tested with openjdk version "17" , though anything above should do fine as well
java -version 


### building maven dependency, this should bring all dependencis required for springboot, run all the junit tests.
maven clean install 
### just in case running with junit test issue, feel free to skip tests.
mvn clean install -Dmaven.test.skip=true

# sanity test to check if spring boot jar (spoken-time-0.0.1-SNAPSHOT.jar) is built 
ls target

### starting the server 
java -jar ./target/spoken-time-0.0.1-SNAPSHOT.jar

```
now open a browser and run below link
http://localhost:8080/spoken-time?time=10:10
and run your test changing 10:10 to any custom input
time format - hh:mm 


	
