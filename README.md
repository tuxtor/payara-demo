# Useful commands for this project

## Standalone JVM

### Run with AutoBindHTTP

```bash
export JDBC_URL='jdbc:postgresql://localhost:5432/movies2'

java -XX:OnOutOfMemoryError="kill -9 %p" -Xms256m -Xmx256m -jar ~/JavaLibs/payara-micro-5.191.jar \
--deploy target/payara-demo.war \
--autobindhttp --nocluster --logo
```

### Cache test
Set 
```bash
curl -X PUT -H "Content-Type: application/json" -d '{"Payara":"Rocks!"}' http://localhost:8080/payara-demo/rest/cache?key=marco
```

Get
```bash
curl http://localhost:8082/payara-demo/rest/cache?key=marco
```

## Docker
Docker build and tag

```bash
docker build -t payara-demo:2019-04-11 .
```

Run with local database
```bash
docker run -e JDBC_URL='jdbc:postgresql://REAL_IP:5432/movies2' -p 8080:8080 payara-demo:2019-04-11
```

Run with docker, Postgres, HAProxy and two service replicas
```bash
docker-compose up --scale payara-demo=2
```

Push to docker registry
Run with local database

## Oracle Cloud
