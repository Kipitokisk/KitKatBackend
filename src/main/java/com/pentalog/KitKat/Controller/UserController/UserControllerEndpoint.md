### **User Controller**
#### _Create User_
##### Description
This endpoint allows for the creation of a new user.
##### Request Body
* `avatar` is the users avatar as BitSet.
* `firstName` is the users first name.
* `lastName` is the users last name.
* `email` is the user's account email.
* `password` is the user's account password.
* `position` is the user's position.
* `seniority` is the user's seniority.
* `city` is the user's city.
* `languagesId` is the user's list of each language's id. Comes as a string separated by comma.
* `cv` is the user's cv as BitSet.
* `role` is the user's role.
* `managerId` is the user's manager assigned via ID.
``` JSON
{
  "avatar": null,
  "firstName": <user_first_name>,
  "lastName": <user_last_name>,
  "email": <user_email>,
  "password": <user_password>,
  "position": null,
  "seniority": null,
  "city": null,
  "languagesId": null,
  "cv": null,
  "role": null,
  "managerId": null
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/user/create' \
--header 'Content-Type: application/json' \
--data-raw '{
  "avatar": null,
  "firstName": <user_first_name>,
  "lastName": <user_last_name>,
  "email": <user_email>,
  "password": <user_password>,
  "position": null,
  "seniority": null,
  "city": null,
  "languagesId": null,
  "cv": null,
  "role": null,
  "managerId": null
}
'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "userId": 1,
    "avatar": null,
    "firstName": <user_first_name>,
    "lastName": <user_last_name>,
    "email": <user_email>,
    "password": {
        "empty": false
    },
    "position": null,
    "seniority": null,
    "city": null,
    "languages": null,
    "cv": null,
    "project": null,
    "skillRating": null,
    "role": null,
    "status": null,
    "managerId": null,
    "languageIdList": []
}
```

#### _Find user by email_
##### Description
This endpoint allows any existing user to be found via email..
##### Request Body
* `email` is the user's account email.
##### Request
``` Curl
curl --location 'http://localhost:8080/user/<user_email>'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "userId": 1,
    "avatar": null,
    "firstName": null,
    "lastName": null,
    "email": <user_email>,
    "password": {
        "empty": false
    },
    "position": null,
    "seniority": null,
    "city": null,
    "languages": null,
    "cv": null,
    "project": null,
    "skillRating": null,
    "role": null,
    "status": {
        "statusId": 2,
        "name": "Pending"
    },
    "managerId": null,
    "languageIdList": []
}
```

#### _Reset user password_
##### Description
This endpoint send the user an email containing a new randomly generated password.
##### Request Body
* `email` is the user's account email.
##### Request
``` Curl
curl --location 'http://localhost:8080/user/reset-password' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "andrei.berco@isa.utm.md"
}'
```
##### Response
Upon execution, if it is a success this endpoint returns status 200 Ok.
If execution failed (wrong email), it returns:
```JSON
{
"message": "User not found"
}
```