## Installation

*	Default active profile is **`test`**. When the application is running, **Flyway** will create the necessary tables and system data along with sample data. In the **`test`** profile, the application uses **H2** database (data in memory).

* 	URL to access application UI: The port is set dynamically and the application will open automatically in the default browser.

* 	Other sample profiles like **`mysql`**, **`production`** are available. Change the **spring.profiles.active** property in the **application.properties** file to any of the aforementioned profiles to use it.

<details open="open">
	<ul>
		<li><a href="#running-the-application-with-ide">Running the application with IDE</a></li>
		<li><a href="#running-the-application-with-maven">Running the application with Maven</a></li>
		<li>
			<a href="#running-the-application-with-executable-jar">Running the application with Executable JAR</a>
			<ul>
				<li>
					<a href="#accessing-data-in-h2-database">Accessing Data in H2 Database</a>
					<ul>
						<li><a href="#h2-console">H2 Console</a></li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</details>

#### Running the application with IDE

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `io.github.anantharajuc.bookmarc.BookmarcApplication` class from your IDE.

* 	Download the zip or clone the Git repository.
* 	Unzip the zip file (if you downloaded one)
* 	Open Command Prompt and Change directory (cd) to folder containing pom.xml
* 	Open Eclipse
	* File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	* Select the project
* 	Choose the Spring Boot Application file (search for @SpringBootApplication)
* 	Right Click on the file and Run as Java Application      
      
#### Running the application with Maven

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ git clone https://github.com/AnanthaRajuC/Bookmarc.git
$ cd Bookmarc
$ mvn spring-boot:run
```

#### Running the application with Executable JAR

The code can also be built into a jar and then executed/run. Once the jar is built, run the jar by double clicking on it or by using the command 

```shell
$ git clone https://github.com/AnanthaRajuC/Bookmarc.git
$ cd Bookmarc
$ mvn package -DskipTests
$ java -jar target/Bookmarc-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
```

To shutdown the jar, follow the below mentioned steps on a Windows machine.

*	In command prompt execute the **jcmd** command to print a list of all running Java processes
*	**Taskkill /PID PROCESS_ID_OF_RUNNING_APP /F** execute this command by replacing the **PROCESS_ID_OF_RUNNING_APP** with the actual process id of the running jar found out from executing the previous command

##### Accessing Data in H2 Database

###### H2 Console

URL to access H2 console: **http://localhost:${server.port}/h2-console/login.jsp**

Fill the login form as follows and click on Connect:

* 	Saved Settings: **Generic H2 (Embedded)**
* 	Setting Name: **Generic H2 (Embedded)**
* 	Driver class: **org.h2.Driver**
* 	JDBC URL: **jdbc:h2:mem:bookmarc;MODE=MySQL**
* 	User Name: **sa**
* 	Password:

<img src="images\h2db\h2-console-login.PNG"/>  

<img src="images\h2db\h2-console-main-view.PNG"/>  

