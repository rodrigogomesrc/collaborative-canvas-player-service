FROM openjdk:19
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","--enable-preview", "-jar","app.jar", "--spring.r2dbc.url=r2dbc:postgresql://player-database-reactive:5432/player"]
