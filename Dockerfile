bFROM gradle:4.8.1-jdk8-alpine as builder
USER root
COPY . .
RUN gradle --no-daemon build

FROM openjdk:8-jre-alpine
COPY --from=builder /home/gradle/build/libs/fint-customer-mailing-list-provisioning-service-*.jar /data/app.jar
CMD ["java", "-jar", "/data/app.jar"]
