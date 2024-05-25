FROM eclipse-temurin:21

RUN adduser --system spring-boot && addgroup --system spring-boot && adduser spring-boot spring-boot
USER spring-boot

WORKDIR /app

COPY target/routing-0.0.1-SNAPSHOT.jar ./application.jar
COPY map.osm map.osm

ENTRYPOINT ["java", "-jar", "./application.jar"]