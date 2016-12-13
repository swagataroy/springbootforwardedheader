# bootprime-base

Questo progetto serve da template per la creazione di applicazioni web che utilizzano Spring Boot e Primefaces


## Sources

http://www.thespringside.com/?p=329

Si è preso spunto da: 
[spring boot jsf](http://www.leveluplunch.com/java/tutorials/037-integrate-jsf-spring-boot/)

[watch integrate jsf spring](https://www.youtube.com/watch?v=KejbFef2sig)


## Le librerie 

### Spring Boot 

Per poter utilizzare SpringBoot si hanno due possibilità:

<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.4.1.RELEASE</version>
		<relativePath /> <!--lookup parent from repository-->
</parent>
	
oppure

<dependencyManagement>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-dependencies</artifactId>
			<version>${spring.boot-version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
	
specificando il goal repackage

<plugin>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-maven-plugin</artifactId>
	<executions>
        <execution>
            <goals>
                <goal>repackage</goal>
            </goals>
        </execution>
    </executions>
</plugin>

Poi serve la dipendenza starter

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

che di default utilizza tomcat


### JSF + Primefaces

Le librerie necessarie per utilizzare JSF e Primefaces

<dependency>
	<groupId>com.sun.faces</groupId>
	<artifactId>jsf-api</artifactId>
	<version>2.2.13</version>
	<scope>provided</scope>
</dependency>
<dependency>
	<groupId>com.sun.faces</groupId>
	<artifactId>jsf-impl</artifactId>
	<version>2.2.13</version>
	<scope>compile</scope>
	<optional>true</optional>
</dependency>
<dependency>
  <groupId>org.primefaces</groupId>
  <artifactId>primefaces</artifactId>
  <version>6.0</version>
</dependency>

JSF's ConfigureListener also has a dependency on JSP, so you'll need to add a dependency on Jasper

<dependency>
  <groupId>org.apache.tomcat.embed</groupId>
  <artifactId>tomcat-embed-jasper</artifactId>
  <scope>provided</scope>
</dependency>

Si è reso necessario anche aggiungere la libreria

<dependency>
	<groupId>javax.inject</groupId>
	<artifactId>javax.inject</artifactId>
	<version>1</version>
</dependency>

perchè da 2.2.10 in poi di mojorra con springboot non funziona più l'annotation @ManagedBean
e si possono usare quindi le annotation CDI (@Inject e @Named) continuando a usare gli scope di JSF.

E' possibile invece utilizzare direttamente @Component e @Autowired anche nei "ManagedBean". 

## Le url di accesso 

http://localhost:8080/
http://localhost:8080/home


http://localhost:8080/test404Error/*
http://localhost:8080/test5xxError/*
http://localhost:8080/testException/*

entrambe che redirigono a index.xhtml


## Le configurazioni

E' necessario definire il solo file faces-context.xml per poter utilizzare i bean di Spring all'interno delle 
view JSF. 

<application>
    <el-resolver>org.springframework.web.jsf.el.SpringBeanFacesELResolver</el-resolver>
</application>


## WebApplicationInitializer

La classe WebApplicationInitializer viene utilizzata per sostituire il file web.xml
http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/WebApplicationInitializer.html
	
WebApplicationInitializer in an interface to be implemented in Servlet 3.0+ environments in order to configure the ServletContext programmatically -- as opposed to (or possibly in conjunction with) the traditional web.xml-based approach.

Implementations of this SPI will be detected automatically by SpringServletContainerInitializer, which itself is bootstrapped automatically by any Servlet 3.0 container. 
	
In Spring Boot all the code from an existing WebApplicationInitializer can be moved into a SpringBootServletInitializer.

A @Bean of type Servlet or ServletRegistrationBean installs that bean in the container as if it was a <servlet/> and <servlet-mapping/> in web.xml.

A @Bean of type Filter or FilterRegistrationBean behaves similarly (like a <filter/> and <filter-mapping/>.


## La gestione degli errori

* Spring Boot provides an /error mapping by default that handles all errors in a sensible way, and it is registered as a ‘global’ error page in the servlet container. 

* If you want to display a custom HTML error page for a given status code, you can add a file to an /error folder. Error pages can either be static HTML (i.e. added under any of the static resource folders) or built using templates. The name of the file should be the exact status code (e.g. 404) or a series mask (e.g. 5xx).

		For example, to map 404 to a static HTML file, your folder structure would look like this:
		
		src/
		 +- main/
		     +- java/
		     |   + <source code>
		     +- resources/
		         +- public/
		             +- error/
		             |   +- 404.html
		             +- <other public assets>
		To map all 5xx errors using a freemarker template, you’d have a structure like this:
		
		src/
		 +- main/
		     +- java/
		     |   + <source code>
		     +- resources/
		         +- templates/
		             +- error.html



Le pagine sotto public/error non leggono il model quindi devono contenere solo testo (da capire come mai)


Con server.error.whitelabel.enabled=false il viewResolver è di tipo InternalViewResolver e in base alle dipendenze di maven le View create sono JstlView(se presente jstl) e ThymeView(se presente thymeleaf)



## Configurazione Thymeleaf

http://blog.codeleak.pl/2014/04/how-to-spring-boot-and-thymeleaf-with-maven.html

Ho dovuto commentare

		<dependency>
			  <groupId>javax.servlet</groupId>
			  <artifactId>jstl</artifactId>
		</dependency>
		
E aggiungere

		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    	</dependency>

## i18

https://examples.javacodegeeks.com/enterprise-java/jsf/jsf-internationalization-example/


