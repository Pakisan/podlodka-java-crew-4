## Podlodka Java Crew 4th season

Demo application for presentation - AsyncAPI for event driven applications

> Allows to ones users send messages to broadcast, and to others subscribe to broadcasting and receive messages

<p float="middle">
  <img src="assets/components.png" width="50%" alt="welcome screen"/>
</p>

### Build

#### Prerequisites
1. JDK 17

If you are using SDKMAN! use [env](https://sdkman.io/usage#env) to prepare environment
```shell
sdk env install
```

### Install locally

Build docker image:
```shell
./gradlew dockerBuildImage
```

Run demo application:
```shell
docker compose up
```

Exposed resources:
- SpringWolf UI will be available on http://localhost:8080/springwolf/asyncapi-ui.html
- SpringDoc UI will be available on http://localhost:8080/swagger-ui/index.html
- Composed AsyncAPI specification will be available on http://localhost:8080/springwolf/docs
- RabbitMQ's management will be available on http://localhost:15672 with next credentials `guest:guest`