FROM amazoncorretto:11-alpine3.16

WORKDIR /app

COPY . /app

RUN ./gradlew build
CMD java -jar build/libs/jvm-jargon-generator-1.0.jar