FROM node:22.14

RUN mkdir -p /home/node/workspace && chown -R node:node /home/node/workspace

WORKDIR /home/node/workspace

RUN npm install -g @angular/cli@19.2.5

RUN apt-get update --fix-missing && apt-get install -y git curl vim

USER node
