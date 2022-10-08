FROM node:18-bullseye

WORKDIR /src/app

RUN npm install -g @vue/cli && \
    npm install -D @vue/cli-service

EXPOSE 8000 9000

ENTRYPOINT ["/bin/bash"]