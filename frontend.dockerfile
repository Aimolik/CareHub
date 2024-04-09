
FROM node:alpine

WORKDIR /app

COPY ./CareHubFrontend/ /app

RUN npm install -g http-server

CMD ["http-server", "-p", "8081"]

EXPOSE 8081
