# BLog API To create blogs, authors, posts, commentators and comments
---

### Spring Boot Application

---
This Project provides to create posts and comments for existing blogs, authors and commentators

##Summary

This is an API for network of blogs, where different blogs can be created,
authors can share different posts on related blogs and commentators can comment
on posts.

## APIs

The application has 5 apis:

* BlogAPI
* AuthorAPI
* PostAPI
* CommentatorAPI
* CommentAPI

```html
POST /v1/blog = creates a new blog
GET /v1/blog/{blogId} = retrieves all blogs
GET /v1/blog/{blogId} = retrieves the blog with given id
PUT /v1/blog/{blogId} = updates the blog with given id
DELETE /v1/blog/{blogId} = drops the given blog
```

These 5 end points above are available as well as
for the other APIs with the same manner.

---
```html
POST /v1/author/{authorId}
...
POST /v1/post/{postId}
...
POST /v1/commentator/{commentatorId}
...
POST /v1/comment/{commentId}
...

```
JUnit test coverage is 100% as well as integration test are available

### Tech Stack

---
* Java 11
* Spring Boot 2.5.5
* Spring Data JPA
* Kotlin 1.5.31
* H2 in memory database
* JUnit 5
* PostgreSQL 13.0
* Flyway 8.0.0


### Prerequisites

---
* Maven
* PostgreSQL


### Run & Build

---
SPORT:8080
```
$ cd FolksDev_Blog/blog-api
$ mvn clean install
$ mvn spring-boot:run
```
---
