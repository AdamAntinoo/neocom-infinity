# Base image
FROM node:20-alpine AS BASEIMAGE
# Create app directory
WORKDIR /app
# A wildcard is used to ensure both package.json AND package-lock.json are copied
COPY package*.json ./
# COPY tsconfig.json ./
# COPY tsconfig.app.json ./

# Install app dependencies
# RUN npm install -g npm@10.5.2
RUN npm install
# Bundle app source
COPY . .
# Fix the environment to the selected level
# COPY ./src/environments/environment..ts ./src/environments/environment.ts
# Creates a "dist" folder with the production build
RUN npm run build

FROM node:20-alpine AS RUNNER
# Set the environment ot be used to configure the service. development-integration-production
COPY --from=BASEIMAGE /app/node_modules /app/node_modules
COPY --from=BASEIMAGE /app/dist /app/dist
COPY --from=BASEIMAGE /app/package.json /app
# COPY --from=BASEIMAGE /app/app-banner.txt /app
COPY --from=BASEIMAGE /app/config /app/config
COPY --from=BASEIMAGE /app/server.js /app

WORKDIR /app
# Expose the port on which the app will run
EXPOSE 6000
# Start the server using the production build
CMD ["node", "server.js"]
