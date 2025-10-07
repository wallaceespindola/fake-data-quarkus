APP_NAME=fake-data-quarkus

.PHONY: run test docker compose clean install dev

run:
	mvn quarkus:dev

dev:
	mvn quarkus:dev

test:
	mvn -B test

docker:
	docker build -t $(APP_NAME):latest .

compose:
	docker-compose up --build

clean:
	mvn -B clean

install:
	mvn clean install -DskipTests
