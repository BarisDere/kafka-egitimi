FROM openjdk:8-jdk-alpine
RUN apk update && apk add --no-cache libc6-compat && \
ln -s /lib/libc.musl-x86_64.so.1 /lib/ld-linux-x86-64.so.2
ARG JAR_FILE=target/*-jar-with-dependencies.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]