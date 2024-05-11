FROM openjdk:17
COPY ./build/libs/omak-backend-0.0.1-SNAPSHOT.jar crm_backend.jar
ENTRYPOINT ["java","-jar","/crm_backend.jar"]
