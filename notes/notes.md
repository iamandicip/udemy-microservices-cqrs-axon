# Master Spring Boot Microservices with CQRS & Event Sourcing

## Principles of Microservices

- Microservices **should not share code or data** (even if this means duplicating code)
- A microservices architecture should have **low coupling and high cohesion** (avoid unnecessary coupling between services)
- Independence and autonomy are **more important than reusability**
- Each microservice should be responsible for a single system function or process
- Microservices **should not communicate directly with each other**; they should make use of an **event / message bus** to communicate with one another
- **Each microservice should believe it is the only service in the world!**

## CQRS

Is a software design pattern that stands for **Command Query Responsibility Segregation**.

Applications should be divided in a **command** and **query** part.

**Command alters** the state of an object or entity, and **queries return** the state of an object or entity.

## Event sourcing

Defines an approach where all of the **changes** that are made to an object or entity **are stored as a sequence of immutable events** to an **event store**, as opposed to storing just the current state.

![Course CQRS Architecture](./Course+Impl+Architecture.svg)

## Axon

Is a platform that consists of the Axon Framework and the Axon Server.

**Axon Framework** is a Java framework that is used to simplify the building of event-driven microservices that are based on CQRS, Event-Sourcing and Domain-Driven Design.

**Axon Server** is an "out of the box" message router and event store that **requires no specific configuration**.

## Domain Model

Describes certain aspects of a system domain that can be used to solve problems within that domain.

## Aggregate

Is an entity or group of entities that is always kept in a consistent state (within a single ACID transaction).

The **Aggregate Root** is the entity within the aggregate that is responsible for maintaining this consistent state.

This makes the aggregate the prime building block for implementing a command model in any CQRS based application.

## Commands

A command is a combination of expressed intent (which describes what you want to be done), as well as the information required to undertake action based on that intent.

Commands are named with a verb in the imperative mood, for example RegisterUserCommand or DepositFundsCommand.

## Events

Are objects that describe something that has occurred in the application. A typical source of events is the aggregate. When something important has occurred within the aggregate, it will raise an event.

Events are always named with the past-participle verb, for example UserRegisteredEvent, or FundsDepositedEvent.

## Queries

Express the desire for information, generally a specific representation of the state of the system.

## Environment setup

```shell
docker swarm init
docker run -d --name axon-server -p 8024:8024 --network springbankNet --restart always axoniq/axonserver:latest
docker run -it -d --name mongo-container -p 27017:27017 --network springbankNet --restart always -v mongodb_data_container:/data/db mongo:latest
docker run -it -d --name mysql-container -p 3306:3306 --network springbankNet --restart always -e MYSQL_ROOT_PASSWORD=springbankRootPsw -v my_sql_data_container:/var/lib/mysql mysql:latest
docker run -it -d --name adminer -p 8080:8080 --network springbankNet --restart always -e ADMINER_DEFAULT_SERVER=mysql-container adminer:latest
```

## User microservices

![User microservices](./Component+Diagram+-+User+Microservices.svg)

## Spring Security & OAuth 2.0

### Spring Security

Is the de facto standard for securing Spring-based applications.

Spring Security features:

- comprehensive and extensible support for Authentication & Authorization
- protection agains attacks
- servlet API integration
- optional integration with Spring MVS

### OAuth 2.0 framework

Enables a third-party application to obtain limited access to an HTTP service.

### JWT (JSON Web Token)

Defines hos access token can be generated and encoded as JSON objects, to enable the secure transmission of data.

## API Gateways & Spring Cloud Gateway

An API Gateway create a **unified entry point** that client applications can use to access microservices.

It acts as a **reverse proxy** that **routes the HTTP requests** that are made by clients **to the correct backend microservices.**

Spring Cloud Gateway provides a library for building an API Gateway on top of Spring MVC.

It provides a simple and effective way to route incoming requests to the appropriate destination using Gateway Handler Mapping.

