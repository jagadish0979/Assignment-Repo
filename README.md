Cognizant Assignment Project
-----------------------------


Getting Started
---------------
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

Prerequisites
-------------
You need to install JDK 1.8

Deployment
===========

Consul Execution
---------------
1)	Download consul.zip file from the shared location.
2)  Extract to a folder
3)	Go to command prompt
4)	Go to consul folder
5)  Rename consul.txt to consul.exe
6)	Execute consul.cmd (It’s a prerequisite for spring boot application)
7)	Go to URL : http://localhost:8050/ui - Here we can see consul  container ui consists of all services
8)	Initially we will be having only consul service is running.
9)	Following is the screen shot for the consul container UI

Spring Boot Application Excecution
----------------------------------------------
1)	Download CognizantAssignments project from the shared location
2)	Go to command prompt
3)	Go to CognizantAssignments project root folder
4)	Execute run.cmd
5)	After executing run.cmd it will build and start the application
6)	Go to URL : http://localhost:8050/ui - Here we can see consul  container ui consists of all services
7)	We can see three services running.
8) make sure consul, CognizantAssignments-service and  CognizantAssignments-Service-management are running with out any issues
9) Go to Spring Boot Application URL 
  http://localhost:9080/assignments
10) You will get a login page
11) Login to the application using admin/admin.
12) If user failed to login following screen will get displayed it will redirect to error login page
13) if success it will go to the main page

Testing
-------
1) JUnit Test cases written for all functionalities. All are getting passed. While executing Spring Boot Application we can see all the tests passed in the maven build.
2) Perform end to end functional testing basing on functionality availbale on the application.

Technologies Used
-----------------
Java 8 Version
Spring Boot - Microservices 
Angular JS - Javascript Framework
Maven - Dependency Management

Versioning
----------
We have used github for versioning. For the versions available, see the branches & tags on this repository.

Authors
-------
JAGADISH ANALA  

License
-------
This project is an assignment project
