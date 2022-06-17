FROM --platform=linux/amd64 openjdk:15-jdk-alpine3.11 AS final

USER root
RUN apk add --update npm=12.22.6-r0
RUN apk add git
ENV APP_DIR=/app/unicommerce
COPY . $APP_DIR
WORKDIR /app/unicommerce/client
RUN npm install
RUN npm run build
WORKDIR $APP_DIR
RUN sh mvnw clean package -s ./deployment/app/settings.xml -Dmaven.test.skip=true
RUN apk --no-cache add curl unzip
RUN curl "http://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip" -o /tmp/newrelic.zip && unzip /tmp/newrelic.zip -d /opt/ && rm /tmp/newrelic.zip

RUN apk update
RUN addgroup -g 1000 -S www-data && adduser -u 1000 -D -S -G www-data www-data

RUN apk add --update tzdata
ENV TZ Asia/Kolkata
RUN cp /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN cp  $APP_DIR/target/unicommerce.jar unicommerce.jar
RUN cp  $APP_DIR/deployment/startup.sh /tmp/startup.sh
RUN cp  $APP_DIR/deployment/app/newrelic.yml newrelic.yml

#newrelic agent
RUN cp /opt/newrelic/newrelic.jar newrelic.jar

RUN chmod +x /tmp/startup.sh

VOLUME /tmp
ENTRYPOINT ["/tmp/startup.sh"]
