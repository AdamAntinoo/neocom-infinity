FROM adoptopenjdk/openjdk11
COPY ./*.jar /usr/app/
COPY ./app-banner.txt /usr/app/
WORKDIR /usr/app
RUN mkdir --parents build/resources/main
#RUN mkdir --parents build/resources/main/properties
# Copy all properties sets into the container. Configuration will select the right set.
#COPY ./properties/* /usr/app/build/resources/main/properties/
COPY ./properties-acceptance/* /usr/app/build/resources/main/properties-acceptance/
ENV PORT=9500
ENV DB_USER=neocom-user
ENV DB_PASSWORD=neocom-password
RUN ls -laR
EXPOSE 9500
ENTRYPOINT ["java", "-jar", "neocom-infinity-backend-acceptance.jar"]
