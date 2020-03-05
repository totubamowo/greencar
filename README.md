# Greencar
GreenCar is a ride-sharing web application to demo a heuristic algorithm for solving a novel Travelling Salesman Problem

## Stack
* Spring MVC (3.2.4.RELEASE)
* Hibernate (4.2.5.Final)
* PostgreSQL
* Maven
* Jetty (6.1.25)
* Twitter Bootstrap (3.0.0)
* jQuery (1.10.2)
* Backbone.js (1.0.0)
* Underscore.js (1.5.2)

## Setting up locally

* Clone the project
```sh
git clone https://bitbucket.org/teerexinc/greencar.git
```
* Download and install PostgreSQL

* Download a client to connect to your local PostgreSQL server.

* Create a database called "greencar"

* Add the dayaUpdate the dbURL bean in db-psql.xml using the credential of the database created (see context.xml to make some changes; tables created automatically).
```
value="postgres://[USERNAME]:[PASSWORD]@[HOST]:[PORT]/[DATABASE_NAME]"
```

* Go to project directory, run
```sh
mvn -D jetty.port=9999 jetty:run
```

* Go to http://localhost:9999/

## Deploying to Heroku

* Create a Heroku account and download Heroku toolbelt

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

## The code base of this project is based on the following boiler plates.
* https://github.com/ParallelBrains/Spring-Hibernate-PostgreSQL-Maven-Jetty
* https://github.com/nicolatassini/Heroku-J2EE-Spring-MVC---Hibernate---PostgreSQL----Maven---Jetty
* https://github.com/heroku/devcenter-spring-mvc-hibernate
* https://devcenter.heroku.com/articles/getting-started-with-spring-mvc-hibernate
