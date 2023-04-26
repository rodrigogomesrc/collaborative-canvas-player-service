## Como rodar

Para compilar o projeto (tem que compilar antes de rodar via docker)
```
./mvnw clean package -DskipTests
```

Para rodar o projeto usando docker
```bash
cd docker
docker-compose up -d
```

Para rodar o projeto sem docker
```
./mvnw spring-boot:run
```

Para rodar o docker sem compose 

./mvnw clean package -DskipTests

sudo docker run --name player-database -p 5435:5432 -e POSTGRES_PASSWORD=player123 -e POSTGRES_USER=player postgres:11


sudo docker build -t player-service:1 .


