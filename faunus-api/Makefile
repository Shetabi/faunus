test:
	./mvnw clean test

build:
	./mvnw clean install

run:
	./mvnw clean package && ./mvnw spring-boot:run

deploy:
	./mvnw clean install && flyctl deploy --local-only