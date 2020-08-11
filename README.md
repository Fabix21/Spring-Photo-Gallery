# Spring-Photo-Gallery
Photo Gallery using Spring Boot and PostgreSQL
Template engine themyleaf, Bootstrap for CSS and HTML styling.

## Live View

Application was deployed on AWS using Elastic Beanstalk and RDS service (PostgreSQL database)
* http://photogallery.us-east-1.elasticbeanstalk.com/

For testing purposes, users and galleries have already been created.

### Test users
```
Login: ADMIN
Password: ADMIN
role: ADMIN

Login: user1
Password: user1
role: USER

Login: user2
Password: user2
role: USER
```

### Available galleries
```
user1: Cats, Dogs

user2: Birds
```
## Running application

Clone it from repo and build with gradle.
* Building app
```
./gradlew build
```
* Running app
```
./gradlew bootRun
```
## Available endpoints
* http://localhost:8080/addUser

REST endpoint for creating new users.
Creating new users is possible by sending JSON request (e.g. via Postman)
for example:
```
{
  "login": "USER",
  "password": "password",
  "role": "USER"
}

```
For creating user with admin role (allowing to create galleries and upload photos)
```
{
  "login": "Admin",
  "password": "password",
  "role": "ADMIN"
}
```

* http://localhost:8080/login

Login form to auth users, when role is `ADMIN` user is redirected to `/addPhoto` endpoint otherwise user is redirected to `/gallery`

* http://localhost:8080/addPhoto

Endpoint allowing user with `ADMIN` role to upload photo to database.

* http://localhost:8080/newGallery

Endpoint allowing user with `ADMIN` role to create new galleries.

* http://localhost:8080/availableGalleries

Endpoint allowing the user to select a gallery to view photos.

* http://localhost:8080/gallery

Endpoint allowing user to view photos from gallery he chose.

* http://localhost:8080/sqlGallery

Endpoint responsible for retrieving image data directly from database in `bytea` format and converting it to `base64`.

(turned off - performance issue)
