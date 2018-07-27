Welcome to the Assignment-Repo wiki!

Cognizant Assignment Project
=============================
Getting Started
----------------
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Prerequisites
-------------
Install JDK 1.8

Deployment
----------

Consul Execution
----------------
1) Download consul.zip file from the shared location.
2) Extract to a folder.
3) Go to command prompt.
4) Go to consul folder.
5) Rename consul.txt to consul.exe
6) Execute consul.cmd (It’s a prerequisite for spring boot application).
7) Go to URL : http://localhost:8050/ui - Here we can see consul container ui consists of all services
Initially we will be having only consul service is running.


Spring Boot Application Execution
-------------------------------------
1) Download CognizantAssignments project from the shared location.
2) Go to command prompt.
3) Go to CognizantAssignments project root folder.
5) Execute run.cmd.
6) After executing run.cmd it will build and start the application.
7) Go to URL : http://localhost:8050/ui - Here we can see consul container ui consists of all services.
8) We can see three services running.
9) make sure consul, CognizantAssignments-service and CognizantAssignments-Service-management are running with out any issues.
10) Go to Spring Boot Application URL http://localhost:9080/assignments.
11) You will get a login page. Login to the application using admin/admin.
12) If user failed to login teh application will redirect to error login page.
13) if success it will go to the main page.

Testing
-------
1) JUnit Test cases written for all functionalities. All test cases are getting passed. While executing Spring Boot Application we can see all the tests passed in the maven build.
2) Perform end to end manual functional testing once basing on functionalities availbale on the application.
3) Download the TestData folder for manual testing.

Technologies Used
-----------------
1) Java Version 8.
2) Spring Boot - Microservices.
3) Angular JS - Javascript Framework. 
4) Maven - Dependency Management.

Versioning
----------
We have used github for versioning. For the versions available, see the branches & tags on this repository.

Authors
-------
JAGADISH ANALA

License
-------
GPL (General Public License). 

This project is an assignment project.

END
---
