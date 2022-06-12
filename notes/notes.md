
# Environment setup

```shell
docker swarm init
docker run -d --name axon-server -p 8024:8024 --network springbankNet --restart always axoniq/axonserver:latest
docker run -it -d --name mongo-container -p 27017:27017 --network springbankNet --restart always -v mongodb_data_container:/data/db mongo:latest
docker run -it -d --name mysql-container -p 3306:3306 --network springbankNet --restart always -e MYSQL_ROOT_PASSWORD=springbankRootPsw -v my_sql_data_container:/var/lib/mysql mysql:latest
docker run -it -d --name adminer -p 8080:8080 --network springbankNet --restart always -e ADMINER_DEFAULT_SERVER=mysql-container adminer:latest
```
