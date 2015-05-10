# Greencar@UoL
GreenCar@UoL is a Java web application project ready to be deployed to Heroku cloud platform
The application is based on Spring, Hibernate, PostgreSQL, Maven and Jetty

Built on Back-end frameworks including:
* Spring MVC (3.2.4.RELEASE)
* Hibernate (4.2.5.Final)
* PostgreSQL
* Maven
* Jetty (6.1.25)

Front-end frameworks including:
* Twitter Bootstrap (3.0.0) and jQuery (1.10.2)
* Backbone.js (1.0.0) and Underscore.js (1.5.2)

## Setting up locally

* Clone the project
```sh
git clone https://bitbucket.org/teerexinc/greencar.git
```
* Download and install PostgreSQL

* Download a client to connect to your local PostgreSQL server. A good one is http://www.pgadmin.org/

* Create a database called "greencar" on port 5433 to avoid port conflicts

* Change dbURL bean in db.xml using the credential of the database created.
```
value="postgres://[USERNAME]:[PASSWORD]@localhost:5433/greencar"
```

* Go to project directory, run
```sh
mvn -D jetty.port=9999 jetty:run
```

* Go to http://localhost:9999/

## Deploying the build to Heroku

* Create a Heroku account and download toolbelt

*  Create an instance on Heroku
```sh
heroku create
```

* setup a free instance of PostgreSQL database server on heroku via the web portal, Check if database environment variable is created
```sh
heroku config
```

* Push the code on Heroku
```sh
git push heroku master
```

* Open the app
```sh
heroku open
```

## This code base of this project is based on the following boiler plate code references.
* https://github.com/ParallelBrains/Spring-Hibernate-PostgreSQL-Maven-Jetty
* https://github.com/nicolatassini/Heroku-J2EE-Spring-MVC---Hibernate---PostgreSQL----Maven---Jetty
* https://github.com/heroku/devcenter-spring-mvc-hibernate
* https://devcenter.heroku.com/articles/getting-started-with-spring-mvc-hibernate