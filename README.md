# Financial House Assignment

* To build project, run:  
    `mvn install dockerfile:build`  
* In order to start the application in docker, run:  
    `docker run -p 8443:8443 -t financialhouse/reporting-api`
* To test running instance, please use Postman export located under _resources/postman_ folder
* In order to avoid Heroku configuration for SSL, JDK, etc. a docker image is being created during build
* To SSL certificates (self-signed), located under _resources/keystore_ is being imported to the docker image itself
* To check coverage, please check _target/jacoco-report_ after build, below is a current screen-shot

![Jococo Report](jococo-report.PNG?raw=true "Jococo Report")
 