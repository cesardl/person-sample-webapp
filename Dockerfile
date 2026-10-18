# Build the WAR on the host first (mvn clean package) — this image only
# runs it, it doesn't build it, so it stays thin and doesn't need Maven,
# the JDK, or your M2 repo baked in.
FROM tomcat:11.0-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*
# Filename becomes the context path on Tomcat's auto-deploy, matching the
# /TrainingSpringMVC path already used by the local SmartTomcat run config
# (.idea/workspace.xml).
COPY target/person-sample-webapp.war /usr/local/tomcat/webapps/TrainingSpringMVC.war

# Overrides jdbc.properties: Spring's PropertySourcesPlaceholderConfigurer
# resolves placeholders against the environment before the properties file,
# so this env var wins over the baked-in jdbc.url without touching the WAR.
# Default assumes this container shares a Docker network with mysql-v8 and
# reaches it by container name on MySQL's internal port (3306, not the
# host-published 3312). Override with `docker run -e JDBC_URL=...` as needed.
ENV JDBC_URL=jdbc:mysql://mysql-v8:3306/crud?zeroDateTimeBehavior=convertToNull

EXPOSE 8080
# Run on cesardl-bridge-network alongside mysql-v8, published on 8080.
# No --restart policy: this is started on demand, not meant to come back
# on its own after a Docker/host restart.
#   docker run -d --name person-sample-webapp --network cesardl-bridge-network \
#     -p 8080:8080 person-sample-webapp:local
