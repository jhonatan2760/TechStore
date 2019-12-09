# TechStore - SpringWebFlux
Simples API using Spring Webflux and Dynamo DB

:construction: UNDER CONSTRUCTION :construction:
:link: https://www.jhonatansouza.com/blog

![technology java](https://img.shields.io/badge/technology-Java-blue.svg)
![technology Gradle](https://img.shields.io/badge/technology-Gradle-blue.svg)
<a href="https://awesomestacks.dev/res-tful-api-with-java-and-spring-boot"><img src="https://awesome.re/badge-flat2.svg"></a>

## Installation

- Requirements to startup the project
    - [**Java 11**](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
    - [**Gradle**](https://docs.gradle.org/current/userguide/userguide.html) | _or use the wrapper ./gradlew_
    - [**Spring Boot 2**](https://spring.io/projects/spring-boot)

### Installing dependencies

To install the dependencies:

````
./gradlew clean install
````

### Running the tests

To run the component integrated tests using Junit, RESTAssured and the LocalDynamoDB instance
you can choose the better way with the both options bellow.

Execute o comando abaixo para executar os test da aplicação
- [**Downloadable DynamoDB Version**](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)
- [**Docker DynamoDB Version**](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.Docker.html)

```
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb

./gradlew clean test
```

## Contributing

Requirements to contribute.

- Feature branch and Git Pull Request
