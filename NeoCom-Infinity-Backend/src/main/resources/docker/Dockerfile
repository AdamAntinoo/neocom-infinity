FROM adoptopenjdk/openjdk11
COPY ./*.jar /usr/app/
COPY ./app-banner.txt /usr/app/
WORKDIR /usr/app
RUN mkdir --parents build/main/resources
# Copy SDE database
COPY ./sde.db /usr/app/build/main/resources/
# Copy all properties sets into the container. Configuration will select the right set.
COPY ./properties-dev/* /usr/app/build/main/resources/properties-dev/
COPY ./properties-acceptance/* /usr/app/build/main/resources/properties-acceptance/
COPY ./properties/* /usr/app/build/main/resources/properties/
ENV PORT=9500
ENV DB_USER=neocom-user
ENV DB_PASSWORD=neocom-password
RUN ls -laR
EXPOSE 9500
ENTRYPOINT ["java", "-jar", "neocom-infinity-backend-container.jar"]
