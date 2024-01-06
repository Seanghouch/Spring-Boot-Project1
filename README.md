# Introduction

1. Build your own docker registry
2. Build docker image
3. Push docker image from local machine to docker registry
4. Pull docker from docker registry
5. How to delete docker repository in docker registry

# Requirement
    Local machine
        - Docker installed

    Server Ubuntu 20.04 LTS for docker registry
        - make sure your server update to date and internet up,if not you apt update & apt upgrade
        - Docker installed
        - Docker-compose installed

### 1. Build your own docker registry
- Configuration ubuntu server step by step as below:

Remote to server by ssh
````
ssh user-name@ip-server 
````
Update server package
````
sudo apt-get update && apt-get upgrade
````
Install docker
````
sudo apt-get install docker.io
````
Install docker-compose
````
sudo apt-get install docker-compose
````
Make directory for store docker-compose file, you can make it for anywhere.
- I make it in, /home/{user-name}/workspace/docker-registry
````
mkdir workspace
````
````
cd workspace/
````
````
mkdir docker-registry
````
````
mkdir volume
````
- You can find **docker-registry.yml** here or u can create file as script below:
````
---
version: '3'

services:
  docker-registry:
    image: registry:2
    container_name: docker-registry
    ports:
      - 5000:5000
    restart: always
    volumes:
      - ./volume:/var/lib/registry

  docker-registry-ui:
    image: konradkleine/docker-registry-frontend:v2
    container_name: docker-registry-ui
    ports:
      - 8080:80
    restart: always
    environment:
      ENV_DOCKER_REGISTRY_HOST: docker-registry
      ENV_DOCKER_REGISTRY_PORT: 5000
````
Run command docker-compose to download image and run container.
- **Noted:** run that command your directory you has been created.
````
sudo docker-compose -f docker-registry.yml up -d
````
command check images, container 
````
sudo docker images
````
````
sudo docker ps
````
check and verify make sure your docker registry frontend it's working.
````
http://localhost:8080
````
### 2. Build docker image
- Build docker image in your local machine.
- if you have your own project u can **skip** this.
- I build my project(spring-boot) to docker images step by step as below:

I use maven to build project to **.jar** file
- **Noted:** before you build **.jar** please check your configuration in application.properties.
- Example: database connection.... 
````
./mvnw clean install
````
You can find **Dockerfile** here or u can create file as script below:
````
FROM openjdk:17
EXPOSE 8080
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]
````
Copy and run command build image
````
docker build -t {your-docker-image-name} .
````