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
COPY ./properties/* /usr/app/properties/
ENV PORT=9500
ENV DB_USER=neocom-user
ENV DB_PASSWORD=neocom-password
RUN ls -laR
EXPOSE 9500
ENTRYPOINT ["java", "-jar", "neocom-infinity-backend-container.jar"]
