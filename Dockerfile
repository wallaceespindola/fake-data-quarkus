# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q -e -B -DskipTests dependency:go-offline
COPY src ./src
RUN mvn -q -e -B -DskipTests package

# Runtime stage
FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.23
ENV LANGUAGE='en_US:en'
WORKDIR /work/

# Copy dependencies and application files
COPY --from=build --chown=185 /app/target/quarkus-app/lib/ /work/lib/
COPY --from=build --chown=185 /app/target/quarkus-app/*.jar /work/
COPY --from=build --chown=185 /app/target/quarkus-app/app/ /work/app/
COPY --from=build --chown=185 /app/target/quarkus-app/quarkus/ /work/quarkus/

EXPOSE 8080
USER 185

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/work/quarkus-run.jar"

ENTRYPOINT [ "java", "-jar", "/work/quarkus-run.jar" ]
