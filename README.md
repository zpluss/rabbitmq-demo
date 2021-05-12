# Getting Started
## docker安装
`docker pull docker.io/rabbitmq:3.8.16-management`
`docker run --name rabbitmq -v /etc/localtime:/etc/localtime:ro \ -d -p 15672:15672 -p 5672:5672 ${容器ID}`
**创建一个用户**
`docker exec -it rabbitmq /bin/bash`
`rabbitmqctl add_user admin 123456`
`rabbitmqctl set_permissions -p / admin ".*" ".*" ".*"`
`rabbitmqctl set_user_tags root administrator`
`exit`
### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-amqp)

### Guides

The following guides illustrate how to use some features concretely:

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)

