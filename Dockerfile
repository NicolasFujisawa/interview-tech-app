FROM openjdk:11.0-jdk-slim

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /app/api

ADD /build/libs/technical-app*.jar technical-app.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005 8080

CMD java ${ADDITIONAL_OPTS} -jar technical-app.jar --spring.profiles.active=${PROFILE}
