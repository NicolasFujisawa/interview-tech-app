dk-build:
	docker-compose build

dk-up:
	docker-compose up --build --force-recreate

dk-dows:
	docker-compose down

build:
	./gradlew build -xtest

clean:
	./gradlew clean