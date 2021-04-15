FROM openjdk:11

USER root
WORKDIR /app/

COPY build/libs/graphql-bff-*.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]

#docker build -t hataketed/ws-graphql-bff:2.2.1 .
#docker push hataketed/ws-graphql-bff:2.2.1
