FROM openjdk:15-alpine
VOLUME /web
EXPOSE 5050
COPY Server/target/modules /app/modules
COPY Server/target/classes /app/Server/
ENTRYPOINT ["java", "--module-path", "/app/Server:/app/modules", "-m", "Server/org.JavaEnthusiast.ServerExample"]