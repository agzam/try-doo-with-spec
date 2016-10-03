FROM java
# install Leiningen
RUN wget https://raw.github.com/technomancy/leiningen/stable/bin/lein && \
    chmod +x lein && \
    mv lein /usr/local/bin/ && \
    LEIN_ROOT=true lein version

ENV LEIN_ROOT=true

# Replace shell with bash so we can source files
RUN rm /bin/sh && ln -s /bin/bash /bin/sh

# make sure apt is up to date
RUN apt-get update --fix-missing
RUN apt-get install -y curl
RUN apt-get install -y build-essential libssl-dev

RUN curl --output /usr/local/bin/phantomjs https://s3.amazonaws.com/circle-downloads/phantomjs-2.1.1 && \
    chmod +x /usr/local/bin/phantomjs 

ENV NVM_DIR /usr/local/nvm
ENV NODE_VERSION 6.1
ENV _JAVA_OPTIONS "-Xms512m -Xmx1024m"
# Install nvm with node and npm
RUN curl https://raw.githubusercontent.com/creationix/nvm/v0.32.0/install.sh | bash \
    && source $NVM_DIR/nvm.sh \
    && nvm install $NODE_VERSION \
    && nvm alias default $NODE_VERSION \
    && nvm use default

ENV NODE_PATH $NVM_DIR/v$NODE_VERSION/lib/node_modules
ENV PATH      $NVM_DIR/versions/node/v$NODE_VERSION/bin:$PATH

WORKDIR /usr/app
COPY . /usr/app
