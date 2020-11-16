dk-build:
	docker-compose build

dk-up:
	docker-compose up --build --force-recreate

dk-down:
	docker-compose down

jdk-build:
	./gradlew build -xtest

jdk-clean:
	./gradlew clean