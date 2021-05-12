# rabbitmq-demo
springboot &amp; rabbitmq demo
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
