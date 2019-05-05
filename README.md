# Photos API
      
**Production URL:** http://ec2-18-219-130-204.us-east-2.compute.amazonaws.com:8080/

---
### Access Token

* POST /oauth/token

> Authorization:

Type: Basic Auth
      Username:xxxx
      Password:xxxx

> Body:  x-www-from-urlencoded

password:xxxxx
username:xxxxx
grant_type:password

---
### User

* GET  /user/id/**{id}**

> Header: Authorization:Bearer **{access_token}**

* GET  /user/**{id}**/albums

> Header: Authorization:Bearer **{access_token}**

* GET  /user/**{id}**/photos

> Header: Authorization:Bearer **{access_token}**

* POST /user/login

> Header: 
Content-Type:application/json
Authorization:Bearer **{access_token}**

> Request Body:
```json
{
    "email": "dabarreiroh@gmail.com",
    "password": "1234564"
}
```

* POST /user/create

> Header: 
Content-Type:application/json
Authorization:Bearer **{access_token}**

> Request Body:
```json
{
  "first_name": "Peter",
  "last_name": "Jhonson",
  "profile_description": "Peter description",
  "user_name": "petjhon",
  "password": "123",
  "email": "peter@gmail.com"
}
```

* PUT /user/**{id}**/edit

> Header: 
Content-Type:application/json
Authorization:Bearer **{access_token}**

> Request Body:
```json
{
  "first_name": "Peter",
  "last_name": "Jhonson",
  "profile_description": "Peter description",
  "user_name": "petjhon"
}
```

---
### Album

* GET  /album/id/**{id}**

> Header: Authorization:Bearer **{access_token}**

* GET  /album/**{id}**/photos

> Header: Authorization:Bearer **{access_token}**

* POST /album/create

> Header: 
Content-Type:application/json
Authorization:Bearer **{access_token}**

> Request Body:
```json
{
  "name": "Vacations",
  "user_id": 2
}
```

---
### Photo

* GET  /photo/id/**{id}**

> Header: Authorization:Bearer **{access_token}**

* GET  /photo/all

> Header: Authorization:Bearer **{access_token}**

* POST /photo/upload

> Header: 
Content-Type:application/x-www-form-data
Authorization:Bearer **{access_token}**

> Request Body:
```
{
  "file": "photo.png", <- File selected by user
  "photo_name": "My Cat",
  "album_id": 2
}
```
