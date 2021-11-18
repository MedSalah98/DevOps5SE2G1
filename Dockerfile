FROM openjdk:8-jre-alpine
#ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \JAVA_OPTS=""

WORKDIR /app
# RUN mkdir -p /home/app

ADD target/*.jar timesheet_devops.jar
# COPY . /home/app


EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/timesheet_devops.jar"] 
#CMD commands to run exemple ( ENTRYPOINTS)
