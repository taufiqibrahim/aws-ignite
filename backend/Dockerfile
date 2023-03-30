FROM openjdk:17-jdk-alpine

COPY target/backend-0.0.1-SNAPSHOT.war backend-0.0.1-SNAPSHOT.war

ENTRYPOINT ["java","-jar","/backend-0.0.1-SNAPSHOT.war", "--spring.profiles.active=${PROFILE:}"]
