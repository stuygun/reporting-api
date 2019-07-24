# Financial House Assignment

## TO-DO(s)
| # | Requirement                           | Status                                 |Notes                                  |
|---|---------------------------------------|----------------------------------------|---------------------------------------|
| 1 | JWT Authentication Token              | OK                                     | Consider error codes. No Bearer prefix|
| 2 | Log all request&response pairs        |                                        |                                       |
| 3 | Dockerize and put certificate for ssl | OK                                     |                                       |
| 4 | Create entity model                   |                                        |                                       |
| 5 | Write API Methods                     |                                        |                                       |
| 6 | Use JUnit5                            | OK                                     |                                       |
| 7 | Hibernate Listener                    |                                        |                                       |
| 8 | Heruko Deployment Instructions        |                                        |                                       |

* To build project, run:
    `mvn install dockerfile:build`  
* In order to start the application in docker, run:  
    `docker run -p 8443:8443 -t financialhouse/reporting-api`  