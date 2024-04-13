FROM ghcr.io/graalvm/graalvm-community:21
WORKDIR /faunus
COPY . .
RUN ./mvnw -Pnative native:compile

FROM debian:stable-slim
VOLUME /tmp
COPY --from=0 /faunus/target/faunus-api /app
EXPOSE 8080
ENTRYPOINT ["/app"]