FROM openjdk:17
COPY ./omak-backend-0.1.jar crm_backend.jar
ENTRYPOINT ["java","-jar","/crm_backend.jar"]
