

.PHONY = build run

build: Dockerfile
	docker build -t databas:latest .

run: build
	docker run -p 1645:1645 -e DISPLAY=192.168.0.105:0 -t databas:latest
