# Spring Cloud Stream + Kotlin + Rabbit example 

Official docs:

https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/spring-cloud-stream.html#_overview

### Workflow

1. Supplier bean `clock` sends an `Instant` to `testin` queue
2. Function bean `convert` consumes message from `testin`, converts it to `String` and sends it to `testout`
3. Consumer bean `log` consumes message and prints log to console

### How to

Run docker compose and app. Example output:

```
Current time is 2023-08-17T07:20:56Z
Current time is 2023-08-17T07:20:57Z
Current time is 2023-08-17T07:20:58Z
Current time is 2023-08-17T07:20:59Z
Current time is 2023-08-17T07:21:00Z
```