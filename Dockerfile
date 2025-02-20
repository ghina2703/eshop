# Build stage
FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /src/eshop

COPY gradlew gradlew.bat gradle/ ./gradle/
COPY build.gradle settings.gradle ./
RUN chmod +x ./gradlew

# Download dependencies first (caching)
RUN ./gradlew dependencies --no-daemon

# Copy all source code & build the app
COPY . .
RUN ./gradlew clean bootJar --no-daemon

# Run stage
FROM docker.io/library/eclipse-temurin:21-jre-alpine AS runner

ARG USER_NAME=eshop
ARG USER_UID=1000
ARG USER_GID=${USER_UID}

RUN addgroup -g ${USER_GID} ${USER_NAME} \
    && adduser -h /opt/eshop -D -u ${USER_UID} -G ${USER_NAME} ${USER_NAME}

USER ${USER_NAME}
WORKDIR /opt/eshop

# Copy JAR file dari build stage
COPY --from=builder --chown=${USER_UID}:${USER_GID} /src/eshop/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
