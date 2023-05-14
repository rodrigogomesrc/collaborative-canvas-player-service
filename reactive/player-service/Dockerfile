FROM openjdk:19
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","--enable-preview", "-jar","app.jar", "--spring.datasource.url=jdbc:postgresql://player-database:5432/player"]