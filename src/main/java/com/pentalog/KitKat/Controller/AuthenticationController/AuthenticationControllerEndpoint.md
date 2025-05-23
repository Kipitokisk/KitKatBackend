### **Authentication Controller**
#### _Login_
##### Description
This endpoint allows any existing user to login.
##### Request Body
* `email` is the user's account email.
* `password` is the user's account password.
``` JSON
{
    "email": <user_email>,
    "password": <user_password>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/api/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": <user_email>,
    "password": <user_password>
}'
```
##### Response
Upon execution, this endpoint returns status 200 OK with message "Verification code sent successfully", or 
```JSON
{
    "message": "User not found"
}
```

#### _Login OTP_
#### Description
This endpoint checks the verification code sent to user.
##### Request Body
* `email` is the user's new account email. To be valid it has to contain '@'
* `verificationCode` is the verification code sent to user's email
````JSON
{
  "email": "<user_email>",
  "verificationCode" : "<verification_code>"
}
````
#### Request
````Curl
curl --location 'http://localhost:8080/api/login-otp' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "<user_email>",
    "verificationCode" : "<verification_code>"
}'
````
#### Response
Upon execution, this endpoint returns a JSON response with the following structure:
````Json
{
    "jwt": "<jwt_token>",
    "id": "<user_id_Integer>",
    "email": "<user_email>"
}
````
In case it fails, the response is:
````Json
{
    "message": "<message>",
    "error": "<error>"
}
````

#### _Register_
##### Description
This endpoint allows any new user to register.
##### Request Body
* `email` is the user's new account email. To be valid it has to contain '@'
* `password` is the user's new account password. To be valid it has to contain:
    * Include lower-case letter(s) [a-z]

    * Include upper-case letter(s) [A-Z]

    * Include numbers [0-9]

    * Include symbols [!@#$%^&*()...]

    * Make it at least 8 characters long.
``` JSON
{
    "email": <user_email>,
    "password": "<user_password>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/api/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": <user_email>,
    "password": <user_password>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "message": "User registered successfully",
    "email": <user_email>
}
```

#### _OAuth2 Sign in_
##### Description
This endpoint uses Google as a sign in method.
##### Response
Upon execution, this endpoint creates/updates the OAuth token and returns a JSON response with the following structure:
``` JSON
{
    "id": <user_id_Integer>,
    "email": <user_email>,
    "jwt": <jwt_token>
}
```