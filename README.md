# custom-appender

./gradlew clean build

http://localhost:8080/

http://localhost:8080/gfg


java -javaagent:/Users/vinitaaher/Projects/opentelemetry-javaagent.jar \
-Dotel.exporter=otlp \
-Dotel.resource.attributes=service.namespace=SpringBootApplication,service.name=Test,host.name=PQN-VAHER \
-Dotel.exporter.otlp.endpoint=http://localhost:4317 \
-Dotel.exporter.otlp.insecure=true \
-jar build/libs/SpringBootApplication-0.0.1-SNAPSHOT.jar

