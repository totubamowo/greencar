# A Java/Spring template project ready to be deployed to Heroku [ ![Codeship Status for denizozger/Spring-Hibernate-PostgreSQL-Maven-Jetty](https://www.codeship.io/projects/5419d2c0-0766-0131-b9ac-5a35253ed5eb/status?branch=master)](https://www.codeship.io/projects/7320)

Built on frameworks including:
* Spring MVC 
* Hibernate 
* PostgreSQL
* Maven
* Jetty

and

(FE frameworks here)

Issues:
* Get app running on localhost:8080 only instead of localhost:8080/baselayout
* Reflect changes on the main application to tests

Improvements:
* Divide controllers to view and data packages
* Find the best profile/properties solution and apply
* Improve Spring security
* Add FE templates/frameworks
* Exception handling (Spring) on controller level
* Enforce checkstyle
* Caching (Redis?)

## Setting up locally

* Clone the project
```sh
git clone https://github.com/denizozger/Spring-Hibernate-PostgreSQL-Maven-Jetty.git
```
* Download and install PostgreSQL

* Download a client to connect to your local PostgreSQL server. A good one is http://www.pgadmin.org/

* Create a database called "base"

* Add DATABASE_URL as an environment variable. (If for some reason it doesn't work, try hardcoding it on root-context.xml)
```sh
export DATABASE_URL=postgres://USERNAME:PASSWORD@localhost:5432/base
```

* Go to project directory, run
```sh
mvn jetty:run
```

* Go to http://localhost:8080/baselayout

## Deploying the build to Heroku

* Create a Heroku account and download toolbelt

*  Create an instance on Heroku
```sh
heroku create
```

* Push the code on Heroku
```sh
git push heroku master
```

* Open the app
```sh
heroku open
```

## Useful links

* https://devcenter.heroku.com/articles/getting-started-with-spring-mvc-hibernate
