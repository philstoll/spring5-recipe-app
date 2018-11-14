# Spring Boot Recipe Application

[![CircleCI](https://circleci.com/gh/springframeworkguru/spring5-recipe-app.svg?style=svg)](https://circleci.com/gh/springframeworkguru/spring5-recipe-app)

This repository is for an example application built in Spring Framework 5. The app 
provides basic CRUD operations for managing recipe information.

This branch provides the ability to run using c-treeACE database.

To get started :

1) Verify, and if needed, modify the c-treeACE hibernate and jdbc jar file location
found in pom.xml

    ```xml
    <dependencies>
        ...
        <dependency>
             <groupId>com.faircome</groupId>
             <artifactId>ctreeACE</artifactId>
             <version>1.0.0</version>
             <scope>system</scope>
             <systemPath>C:/FairCom/V11.5.0/winX64/sdk/sql.hibernate/ctreeACEDialect.jar</systemPath>
         </dependency>
         <dependency>
             <groupId>com.faircom</groupId>
             <artifactId>ctreeJDBC</artifactId>
             <version>1.0.0</version>
             <scope>system</scope>
             <systemPath>C:/FairCom/V11.5.0/winX64/lib/sql.jdbc/ctreeJDBC.jar</systemPath>
         </dependency>
         ...
    </dependencies>
    ```
 
2) Modify the spring.datasource settings at src/main/resources/application-devctree.yml
  to match the c-treeACE database and user that you will be using.
 
3) Execute the _Create Tables_ and _Add Indexes_ sections of the
src/main/scripts/config-ctree.sql on the database specified in the settings from step 2.

4) Set the Spring active profile to _ctree_ at src/main/resources/application.properties:

    `spring.profiles.active=ctree`
    

The application can be built and run using Maven. If running the app with
MySQL, you will need to run the SQL script found at /src/main/scripts/config-mysql

After starting the application, you should be able to access it at http://localhost:8080

