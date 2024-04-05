ARG BUILD_IMAGE=maven:3.8.4-openjdk-17
ARG RUNTIME_IMAGE=openjdk:17-alpine
#############################################################################################
###                Stage where Docker is pulling all maven dependencies                   ###
#############################################################################################
FROM ${BUILD_IMAGE} as dependencies

WORKDIR /app

COPY pom.xml ./
COPY ./*.config ./

RUN mvn -B dependency:go-offline
#############################################################################################

#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM dependencies as build

COPY src ./src

RUN mkdir ./target

RUN mvn -B clean package

#############################################################################################


#############################################################################################
###   Image config                                   Main stage                                      ###
#############################################################################################

FROM ${RUNTIME_IMAGE} as main

ARG TARGET_DIR=target

WORKDIR /opt/app

EXPOSE 8080

COPY --from=build /app/target/*.jar /opt/app/service.jar

RUN apk update && apk upgrade

ENTRYPOINT ["java","-jar","/opt/app/service.jar"]
#############################################################################################