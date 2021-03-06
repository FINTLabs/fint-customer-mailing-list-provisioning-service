FROM gradle:4.10.2-jdk8-alpine as builder
USER root
COPY . .
RUN gradle --no-daemon build

FROM gcr.io/distroless/java
ENV JAVA_TOOL_OPTIONS -XX:+ExitOnOutOfMemoryError
COPY --from=builder /home/gradle/build/libs/fint-customer-mailing-list-provisioning-service-*.jar /data/app.jar
CMD ["/data/app.jar"]
