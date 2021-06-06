## Table of Contents

<details open="open">
   <ul>
      <li>
		 <a href="#dockerfile-maven">Dockerfile Maven</a>
         <a href="#installing">Installing</a>
         <ul>
            <li>
               <a href="#running-the-application-manually-via-docker-container">Running the application manually via docker container</a>
               <ul>
                  <li><a href="#basic-docker-commands-for-reference">Basic Docker commands for reference</a></li>
                  <li><a href="#docker-hub-commands-for-reference">Docker Hub Commands for Reference</a></li>
               </ul>
            </li>
         </ul>
      </li>
   </ul>
</details>

## Dockerfile Maven

Here we build and push the application's docker image to DockerHub.

This [Maven plugin](https://github.com/spotify/dockerfile-maven) integrates Maven with Docker. Update the **pom.xml** file with your **DockerHub username** and execute the maven install command **mvn install**. Execution of this command will result in the generation of the application jar file, building of the Docker image and pushing of this newly created image to DockerHub.

```
<!--  Plugin for building and pushing Docker image to Docker Hub. -->
<plugin>
	<groupId>com.spotify</groupId>
	<artifactId>dockerfile-maven-plugin</artifactId>
	<version>1.4.13</version>
	<configuration>
		<repository>DOCKER_HUB_USERNAME/${project.artifactId}</repository>
		<tag>${project.version}</tag>
		<buildArgs>
			<JAR_FILE>target/${project.artifactId}-${project.version}.jar</JAR_FILE>
		</buildArgs>
	</configuration>
	<executions>
		<execution>
			<id>default</id>
			<phase>install</phase>
			<goals>
				<goal>build</goal>
				<goal>push</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

## Installing

#### Running the application manually via docker container

* 	[anantha/bookmarc](https://hub.docker.com/repository/docker/anantha/bookmarc/general) - DockerHub Image

DockerHub Pull Command if you want to directly pull the docker image of the application from Docker Hub.

```shell
docker pull anantha/bookmarc
```

**NOTE:** If you want to build a docker image from the source code, ensure you build a jar of the application before building a docker image.  

```shell
$ mvn package -Dmaven.test.skip=true     //skip all tests and build. The build once completed is available in **target** folder
```

```shell
$ mvn clean package                      //run all tests and build
```

A runnable jar file gets built and is available in the **target** folder

On Windows machine use **Docker Quickstart Terminal** or, use **Windows Powershell** and navigate to the project folder where Dockerfile is present.

##### Basic Docker commands for reference

Checkout additional Docker and DockerHub commands here, [https://github.com/AnanthaRajuC/Hacks-and-Code-Snippets/blob/master/Docker.md](https://github.com/AnanthaRajuC/Hacks-and-Code-Snippets/blob/master/Docker.md) 

|                           Command                                  |                                     Description                               |
|--------------------------------------------------------------------|-------------------------------------------------------------------------------| 
|`docker-machine ip default`							             | check your docker IP default, usually it is **192.168.99.102**			     |
|`docker images`                                                     | take a look at the container images.                                          |
|`docker ps`                                                         | list all the running containers.                                              |
|`docker ps -a`                                                      | list all the containers, including the ones that have finished executing.     |
|`docker restart [container_name]`							         | restart the docker image			                             		         |
|`docker stats`							                             | Show CPU and memory usage of all running containers                 	         |
|`docker stats [container_name]`						             | Show CPU and memory usage of a particular running container                   |
|`docker stats [container1_name] [container2_name]`			         | Show CPU and memory usage of container1, container2                           |
|`docker top [container_name]`			                             | Show running processes in a container                                         |
|`docker system df`			                                         | Show storage usage                                                            |
|`docker logs [container_id]`			                             | list container logs                                                           |
|`docker logs [container_id] --tail N`                               | list container logs, **`--tail`** flag will show the last **N** lines of logs |   
|`docker logs [container_id] --since YYYY-MM-DD`                     | list container logs since a particular date                                   |
|`docker logs [container_id] --since YYYY-MM-DDTHH:MM:SS.000000000Z` | list container logs since a particular timestamp                              |

##### Docker Hub Commands for Reference     

|                               Command                              |                         Description                               |
|--------------------------------------------------------------------|-------------------------------------------------------------------| 
|`docker logout`							                         | logout of Docker Hub from the local machine.                      |
|`docker login --username=YOUR_DOCKERHUB_USERNAME`	                 | login to Docker Hub from your machine.                            |
|`docker tag <existing-image> <hub-user>/<repo-name>[:<tag>]`        | re-tagging an existing local image					             |
|`docker commit <existing-container> <hub-user>/<repo-name>[:<tag>]` | commit changes					                                 |
|`docker push <hub-user>/<repo-name>:<tag>`                          | push this repository to the registry designated by its name or tag|

**Examples:**

*	re-tagging an existing local image : `docker tag bookmarc anantha/bookmarc:latest`
*	commit changes                     : `docker commit bookmarc anantha/bookmarc:latest`
*	docker push                        : `docker push anantha/bookmarc:latest`
