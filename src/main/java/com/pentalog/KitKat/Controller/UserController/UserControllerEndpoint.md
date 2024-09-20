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

#### _Update user info_
##### Description
This endpoint updates user info.
##### Request Body
* `userId` is the user's id.
* `firstName` is the user's first name.
* `lastName` is the user's last name.
* `avatar` is the user's avatar (Type: BitSet).
* `position` is the user's position (e.g. IT technician, Web developer).
* `seniority` is the user's seniority (e.g. Junior, Mid, Senior).
* `city` is the user's city.
* `languages` is the user's languages.
* `cv` is the user's cv (Type: Bitset).
##### Request
```Curl
curl --location --request PUT 'http://localhost:8080/user/update' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=611504409644BD821112F959E3D99EB4' \
--data '{
    "userId": <user_id>,
    "firstName": <user_firstName>,
    "lastName": <user_lastName>,
    "avatar": <user_avatar>,
    "position": <user_position>,
    "seniority": <user_seniority>,
    "city": <user_city>,
    "languages": <user_languages>,
    "cv": <user_cv>
}'
```
##### Response
Upon execution, this endpoint returns the message "User updated successfully".

#### _Reset user info_
##### Description
This endpoint resets user info.
##### Request
```Curl
curl --location --request PUT 'http://localhost:8080/user/reset/<user_id>' \
--header 'Cookie: JSESSIONID=611504409644BD821112F959E3D99EB4'
```
##### Response
Upon execution, this endpoint returns the message "User info reset successfully".
