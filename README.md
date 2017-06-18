# RandomText client

RandomText client (unofficial) grabs remote data from http://www.randomtext.me service, and shows some stats. The main goal was to reach an optimal balance in code between testable and scalable, and evaluate RxJava at the task.

  - Async and multithreaded http client usage example 
  - Sync read async write to DB, motivated by [CQRS]
  - Feature based source code organization
  - [API docs] is produced using [Swagger]
 

### Tech

RandomText client uses a number of open source projects to work properly:

* [retrofit] - A type-safe HTTP client for Android and Java
* [RxJava] - Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM
* [Spring Boot] - makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run"
* [Spring Data JPA] - part of the larger Spring Data family, makes it easy to easily implement JPA based repositories
* [Liquibase] - source control for your database
* [h2] - Database Engine
* [Apache Maven] - is a software project management and comprehension tool
* [Swagger] - is the world’s largest framework of API developer tools for the OpenAPI Specification

### Installation

The application requires [Apache Maven] v3.2.+ to run, according to the Spring Boot requirements.

### Development

Want to contribute? Great!

Run unit (*Test.java) tests:
```sh
$ mvn clean test
```

Run unit&integration (*IT.java) tests:
```sh
$ mvn clean install
```

#### Building for source
For production release:
```sh
$ mvn clean install
```

### Todos

 - none

License
----

Apache 2.0


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [retrofit]: <http://square.github.io/retrofit/>
   [RxJava]: <https://github.com/ReactiveX/RxJava>
   [Spring Boot]: <http://projects.spring.io/spring-boot/>
   [Spring Data JPA]: <http://projects.spring.io/spring-data-jpa/>
   [Liquibase]: <http://www.liquibase.org/index.html>
   [h2]: <http://www.h2database.com/html/main.html>
   [Apache Maven]: <https://maven.apache.org/>
   [CQRS]: <https://martinfowler.com/bliki/CQRS.html>
   [API docs]: <http://localhost:8080/swagger-ui.html>
   [Swagger]: <http://swagger.io/>

