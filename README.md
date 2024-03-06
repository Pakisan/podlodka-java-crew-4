## Podlodka Java Crew 4th season

Demo application for presentation - AsyncAPI for event driven applications

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