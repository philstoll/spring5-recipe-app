# Spring Boot Recipe Application

[![CircleCI](https://circleci.com/gh/springframeworkguru/spring5-recipe-app.svg?style=svg)](https://circleci.com/gh/springframeworkguru/spring5-recipe-app)

This repository is for an example application built in Spring Framework 5. The app 
provides basic CRUD operations for managing recipe information.

It utilizes spring active profiles to toggle between an H2 in memory database 
and MySQL with options for development and production deployments.

By default the the application will use the H2 db. To switch to MySQL you can
can add the following to the /src/main/resources/application.properties file:
    
    spring.profiles.active=dev      //For Development or
    spring.profiles.active=prod     // For Production
    
There is also an integration with c-treeACE database. This can be accessed from the
_ctree_ branch. For right now a separate branch was created because the domain
object properties needed to be modified due to issues with @Lob
column types using c-treeACE hibernate dialect.

The application can be built and run using Maven. If running the app with
MySQL, you will need to run the SQL script found at /src/main/scripts/config-mysql

If you modify any database names or user credentials, you will need to also modify
/src/main/resources/application-dev.yml or /src/main/resources/application-prod.yml

After starting the application, you should be able to access it at http://localhost:8080

