dk-build:
	docker-compose build

dk-up:
	docker-compose up --build

dk-up-recreate:
	docker-compose up --build --force-recreate

dk-down:
	docker-compose down

.PHONY: build
build:
	./gradlew build -xtest

jdk-clean:
	./gradlew clean

postgres:
	docker exec -it technicalapp_db bash