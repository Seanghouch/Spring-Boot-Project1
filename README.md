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
This Command check your repository in docker registry
````
curl -X GET http://{ip-docker-registry}:5000/v2/_catalog
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
### 3. Push docker image from local machine to docker registry
After build that image, you need to tag image for push to server
````
docker tag spring-boot {ip-docker-regisry}:5000/spring-boot
````
Before push image to docker registry u need to config insecure-registries
- **Noted:** Local machine is meaning you using that machine for build your own image.
1. if your local machine is windows, you can follow step as below:
   - Open docker desktop
   - Click setting
   - Click docker engine
   - Add "insecure-registries": ["{ip-docker-registry}:5000"]
   - Apply & restart

2. if your local machine is linux, you can follow step as below:
   - vim /etc/docker/daemon.json
   - Add "insecure-registries": ["{ip-docker-registry}:5000"]
   - Save and run command restart docker
````
sudo systemctl restart docker
````
You can push your image to your docker registry
- **Noted:** {ip-docker-registry}:5000/spring-boot, that mean your docker image has been tag above.
````
docker push {ip-docker-registry}:5000/spring-boot
````
This Command check your repository in docker registry
````
curl -X GET http://{ip-docker-registry}:5000/v2/_catalog
````
You check and verify your docker registry by web.
````
http://localhost:8080
````
### 4. Pull docker from docker registry
Before pull please remove your images has been push on step first
````
docker rmi -f {image-name}
````
Pull docker image from docker registry
````
docker pull {ip-docker-registry}/spring-boot
````
your can check your docker images to verify.
````
docker images
````
Noted: if you have other local machine or your team work want to pull from that docker registry, you need to config **insecure-registries**.
````
1. if your local machine is windows, you can follow step as below:
   - Open docker desktop
   - Click setting
   - Click docker engine
   - Add "insecure-registries": ["{ip-docker-registry}:5000"]
   - Apply & restart

2. if your local machine is linux, you can follow step as below:
   - vim /etc/docker/daemon.json
   - Add "insecure-registries": ["ip-docker-registry:5000"]
   - Save and run command restart docker
````
Run container
````
sudo docker run -it -d --restart always -p 8181:8080 --name spring-boot {your-image-name}
````
Update container start, when docker start up.
````
sudo docker update --restart always {your-container-id}
````
### 5. How to delete docker repository in docker registry
Before delete I want to remind you, that we have create folder in docker registry,<br>
we have created **/home/{user-name}/workspace/docker-registry** <br>
if your don't create directory like me please re-check it.
````
cd workspace/docker-registry/volume/docker/registry/v2/repositories/
````
Run command ls to check your folder
````
ls
````
Then you can use command remove directory as below 
````
sudo rm -r {your-directory}
````
Then you can check your repository by two ways as below
````
curl -X GET http://{ip-docker-registry}:5000/v2/_catalog
````
````
http://localhost:8080
````
# <center>THANK YOU!</center>