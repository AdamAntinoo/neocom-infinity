FROM adoptopenjdk/openjdk11
COPY ./*.jar /usr/app/
COPY ./app-banner.txt /usr/app/
WORKDIR /usr/app
#RUN mkdir --parents build/main/resources
# Copy SDE database
COPY ./sde.db /usr/app/
# Copy all properties sets into the container. Configuration will select the right set.
COPY ./properties-dev/* /usr/app/properties-dev/
COPY ./properties-acceptance/* /usr/app/properties-acceptance/
COPY ./properties-integration/* /usr/app/properties-integration/
COPY ./properties-staging/* /usr/app/properties-staging/
COPY ./properties/* /usr/app/properties/

ENV PORT=9500
ENV NEOCOM_DATABASE_URL="jdbc:postgresql://localhost:5432/neocom?user=adamantinoo&password=z.iliada.2020"
ENV SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/postgres"
ENV SPRING_DATASOURCE_USERNAME="adamantinoo"
ENV SPRING_DATASOURCE_PASSWORD="z.iliada.2020"
ENV APPLICATION_DIRECTORY="/app/NeoCom.Infinity.Integration"
ENV NEOCOM_BANNER_LOCATION="/app/resources/main/app-banner.txt"
ENV PROFILE="staging"
ENV PROPERTIES_DIRECTORY="/app/resources/main/properties-staging"
ENV REDIS_URL="redis://localhost:5250"
ENV SDE_DATABASE_PATH="/app/resources/main/sde.db"

EXPOSE 9500
ENTRYPOINT ["java", "-jar", "neocom-infinity-backend-container.jar"]
