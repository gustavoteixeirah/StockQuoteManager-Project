FROM amazoncorretto:8-alpine
RUN apk --no-cache add curl
VOLUME /tmp
#ARG URI
#ENV DB_URI=${URI}
ARG JAR_FILE=target/stock-quote-manager-v1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]