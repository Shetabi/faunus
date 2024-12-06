test:
	./mvnw clean test

connect-db:
	flyctl postgres connect -a faunus-db -d faunus

build:
	./mvnw clean install

run:
	./mvnw clean package && ./mvnw spring-boot:run

deploy:
	./mvnw clean install && flyctl deploy
