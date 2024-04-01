FROM node:20.11.1-alpine AS BASEIMAGE

COPY package*.json .
COPY tsconfig.json .
COPY tsconfig.build.json .
COPY nest-cli.json .
RUN npm clean-install
COPY ./src .
RUN npm run package

FROM node:20.11.1-alpine

WORKDIR /src
ENV TZ=Europe/Istanbul
COPY --from=BASEIMAGE /src/dist /src/dist
COPY --from=BASEIMAGE /src/node_modules /src/node_modules
EXPOSE 3000

CMD ["node", "dist/main.js"]
