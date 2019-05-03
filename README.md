# Photos API
* Production URL: http://ec2-18-219-130-204.us-east-2.compute.amazonaws.com:8080/

---
### User

* GET  /user/id/**{id}**
* GET  /user/**{id}**/albums
* GET  /user/**{id}**/photos

* POST /user/create
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
* GET  /album/**{id}**/photos

* POST /album/create
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
* POST /photo/upload/
> Request Body:
```
{
  "file": "photo.png", <- File selected by user
  "photo_name": "My Cat",
  "album_id": 2
}
```
