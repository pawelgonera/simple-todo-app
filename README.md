# simple-todo-app
Simple todo application.

### Purpose
This app was made for task created by [1024kb-pl](https://github.com/1024kb-pl) for the needs of the 
[StowarzyszenieNaukiJavy](https://github.com/1024kb-pl/StowarzyszenieNaukiJavy) project. Purpose of this project is for learning JAVA Programming.

### Description
In this application you can create new task with title, short description, and a date that says when the task ends. You can also display all tasks
for specific registered account who is currently log in app. Each task can be modify and removed. List of the task can be sorted and filtered
by title, date and also finished or unfinished tasks.

### Technology
Application has been developed as web service using Spring Boot with Spring MVC, Spring Security and Spring Data JPA.

#### Tools
* Java 8
* Spring Boot
* Maven
* Thymeleaf
* MySQL
* Apache Tomcat
* Bootstrap
* Jenkins

### Deployment
Application has been deployed on VPS using Continuous Integration with Jenkins on this [URL](http://54.37.139.130:8080/SimpleToDoApp/).

#### How to deploy on localhost

Clone this repo
```
git clone https://github.com/pawelgonera/simple-todo-app
```

You must have tomcat server and maven installed, change settings in two files

In maven directory
```
[maven-home-directory]/conf/settings.xml
```
add this in servers section
```
<server>
  <id>TomcatServer</id>
  <username>tomcat</username>
  <password>password</password>
</server>
```

In tomcat directory
```
[tomcat-home-directory]/conf/tomcat-users.xml
```
Replace all tomcat user setting to (remove comment tags)
```
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="tomcat" password="password" roles="manager-gui,manager-script"/>
```

And run server - go to directory where tomcat is installed
```
cd [tomcat-home-directory]/bin/startup
```

Then deploy war file on tomcat using maven
```
mvn tomcat7:deploy
```

Go to url
```
localhost:8080/simple-todo-app
```

### Author
[Paweł Gónera](https://www.linkedin.com/in/pawe%C5%82-g%C3%B3nera-87055aa2/)

