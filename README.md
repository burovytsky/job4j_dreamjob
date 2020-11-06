[![Build Status](https://travis-ci.org/burovytsky/job4j_dreamjob.svg?branch=master)](https://travis-ci.org/burovytsky/job4j_dreamjob)
[![codecov](https://codecov.io/gh/burovytsky/job4j_dreamjob/branch/master/graph/badge.svg)](https://codecov.io/gh/burovytsky/job4j_dreamjob)

## Project "DreamJob"

This project has implemented an example of a simple web application for working with vacancies and candidates.

Used technologies:
- Apache Tomcat
- PostgresSQL
- Liquibase library for DB
- JS,AJAX,JSP (front)
- Logging system Slf4j with log4j
- Mockito with PowerMock for test cases
- Maven as a build system

Login page - the application implements registration and authentication of 
application users. All pages can only be viewed by authorized users.
![alt text](screenshots/login_page.jpg "login page")


The page for adding/editing a candidate with field validation
![alt text](screenshots/new_candidate_page.jpg "new candidate page")
![alt text](screenshots/field_validation.jpg "new candidate page")


Candidates Page - Shows a list of candidates. On the left there is
 an icon for editing data and an icon for deleting a candidate 
 from the database. On the right is a link to download a photo
![alt text](screenshots/candidates_page.jpg "candidates page")
