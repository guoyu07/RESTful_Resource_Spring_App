[![Build Status](https://travis-ci.org/tanmoy281/RESTful_Resource_Spring_App.svg?branch=master)](https://travis-ci.org/tanmoy281/RESTful_Resource_Spring_App)

# RESTful_Resource_Spring_App
This repository contains all the projects of a restful API module developed using Spring Framework. Best practices and patterns are followed while developing the application.

You need to place sqljdbc42.jar file to SimpleSpringRestWAR/libs folder, As this artifact is not available in maven central repository.

Required: MS SQL Server 2014 Express and AdventureWorks2014 database.

It contains 4 projects:

**1. SimpleSpringRestWAR :**
This is the web projects which will create a single war file that can be deployed in application server like Tomcat. It is developed using spring mvc and uses DispatcherServlet as a front controller.

**2. SpringREST :**
This is a java project which contains all the spring rest controllers for handling requests from DispatcherServlet defined in SimpleSpringRestWAR project.

**3. EmployeeServiceContract :**
This is a java project which contains service contracts of EmployeeRepositoryService project and is reusable. It contains service interface and DTOs which are used by rest controllers of SpringREST project.

**4. EmployeeRepositoryService :**
This is also a java project and it contains implementations of service interfaces defined in EmployeeServiceContract project. Apart from those it also contains DAO interfaces and DAO implementations (Repositories) used by service implementations. This project is also resuable.


Sample URLs: 

http://localhost:8080/SimpleSpringRestWAR/restful/employee/all
http://localhost:8080/SimpleSpringRestWAR/restful/employee/330211482

**Updated: 28th May'17**

Added 3 new projects to include quartz scheduler:

QuartzJobSchedulerService

SimpleRepositoryServiceContract

SimpleRepositoryService


**Updated: 29th May'17**

Added 1 project to include authentication( http basic authentication mechanism is used here):

AuthModule
