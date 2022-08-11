# Spring Boot "GENDRA API" Example Project

This is a sample Java / Maven / Spring Boot (version 2.7.2) application that can be used as a starter for replicate a microservice complete with built-in. I hope it helps you.

## How to Run 

This application is packaged as a ```jar``` which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using OpenJDK 1.8 and Maven 4.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.5.0.jar
or
        mvn spring-boot:run
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

## About the Service

The service is just a REST API endpoint that provides auto-complete suggestions for large cities.
 in ```com.brian.gendraapi.SuggestionController``` on **port 8090**. (see below)


### Some endpoints for controller (SuggestionController).

```
https://localhost:8080/gendra/suggestions
```

### Search some cities with parameters 
?q=A&latitude=42.98339&longitude=-81.8

```
GET /gendra/suggestions?q=A&latitude=42.98339&longitude=-81.8
Accept: application/json
Content-Type: application/json

{

}

RESPONSE: HTTP 200 (Created)
```

### To view Swagger API docs

Run the server and browse to localhost:8080/swagger-ui.html

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:


### In pom.xml add: 

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.univocity</groupId>
            <artifactId>univocity-parsers</artifactId>
            <version>2.9.1</version>
        </dependency>
        <dependency>
            <groupId>me.xdrop</groupId>
            <artifactId>fuzzywuzzy</artifactId>
            <version>1.4.0</version>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-all</artifactId>
            <version>2.0.2-beta</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
```

### Append this to the end of application.yml: 

```
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```
