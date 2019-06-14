# EmojiHW Translation
---
Emojis have become a fun and fast way of communication of modern society. The objective of the Emoji translator is to translate text entered by the registered users based on their unique codes.
This aapilication is developed using Spring MVC, Spring Data, Spring RESTful web service, Maven, PostgresSql, Docker, Amazon SQS, and Amazon S3.

#### User Stories
---
1. Users need to register on the website first by entering their name and e-mail address and creating their username and password.
2. Registered users can enter a string of words, numbers, and punctuation into a text box.
3. Registered users can see the emoji translation immediately after finishing typing the contents. 
4. Text elements for which there is no emoji will be left unchanged.

#### Approach
---
1. Created Users, Conversations, and Emojis objects; and created related table and columns in the database.
2. The relation between User and Conversation is One to Many, the user_id will be the foreign key that will store in the Conversation table.
3. The relation between Conversation and Emoji is One to One, the conversation_id will be the foreign key that will store in the Emoji table.

#### Building Project
---
1. Clone the project

```(git clone https://github.com/pinzhou0504/emoji_translation.git)```

2. Install docker if needed. Please use docker maven openjdk and select the 3.6-jdk-8 version.

```3.6.0-jdk-8, 3.6-jdk-8, 3-jdk-8 (jdk-8/Dockerfile)```

3. Open a new command line window and Spin up the PostgreSql database server using Postgres docker image

```docker pull PostgreSQL```

```bash
docker run --name ${EmojiHW} -e POSTGRES_DB=${EmojiHW_dev} -e POSTGRES_USER=${db_username} -e POSTGRES_PASSWORD=${db_password} -p 5432:5432 -d postgres
```
4. Create Unit database on PGAdmin for unit testing

```create database emojiHW_unit;```

#### Database Migration
---
5. Schema migration for creating tables in database for dev environment on emojiHW-MVC folder.

```bash
mvn compile flyway:migrate -Ddb.url=${localhost:5432/emojihw_dev} -Ddb.password=${password} -Ddb.username=${username}
```


#### Testing Results
---
6. Tests are done using JUnit and Mockito. Tests are run using the command on emoji folder.

```bash
mvn compile test -Dspring.profiles.active=${unit} -Daws.region=${region} -Ddb.url=${localhost:5432/emojihw_unit} -Ddb.username=${username} -Ddb.password=${password} 
```
#### Emoji Reference Demo
---
1. Health Check Application
```
GET - http://localhost:8080/api/test/check
```

```
{
    "Application running okay"
}
```

2. User sign up to post conversation content

```POST - http://localhost:8080/api/user/signup```

Requestbody

```
{
"username":"9",
"email":"9@gmail.com",
"password":"9",
"lastName": "9",
"firstName": "9"
}
```
3. User Login with Token

```POST - http://localhost:8080/api/user/login```
![signup](https://raw.githubusercontent.com/pinzhou0504/emoji_translation/master/images/Screen%20Shot%202019-06-13%20at%204.37.29%20PM.png)

ResponseEntity:

```
{
"token:": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5IiwiY3JlYXRlZCI6MTU2MDQ1ODIyNzg3NiwiZXhwIjoxNTYwNTQ0NjI3fQ.s9o2X1g032w4oztF150mdyV_hXsynYG_CeK5u_j8nxl3otjUco-xsUXMp6r4dvf122UF_g_ZN8Ar8qUxPXGIHA"
}
```

![token](https://raw.githubusercontent.com/pinzhou0504/emoji_translation/master/images/Screen%20Shot%202019-06-13%20at%204.37.38%20PM.png)

4. Input the JwtToken with Serect Key and use findByUsername pass the Username by using @RequestParam.
 ```GET - http://localhost:8080/api/user/?first_name=Chris  ```
 ResponseEntity:
 ```
 {
     "id": 13,
     "username": "9",
     "firstName": "9",
     "lastName": "9",
     "password": "$2a$10$fLJLP.wnwl50uUJMH5LkcOxlxy7A8eFH4yyd1ohf5XkvJ35S8AJZK",
     "email": "9@gmail.com"
 }
 ```
 ![findByUsername](https://raw.githubusercontent.com/pinzhou0504/emoji_translation/master/images/Screen%20Shot%202019-06-13%20at%204.38.59%20PM.png)
 


Userful links

[Full Emoji List v12.0](https://unicode.org/emoji/charts/full-emoji-list.html)

Example project

[Emoji Translate](https://emojitranslate.com/)
