FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} securecarpark.jar
ENTRYPOINT ["java","-java", "/securecarpark.jar"]
