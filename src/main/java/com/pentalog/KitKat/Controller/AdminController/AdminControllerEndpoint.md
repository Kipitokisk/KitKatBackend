### **Admin Controller**
#### _Create Country_
##### Description
This endpoint allows the ADMIN to create a new country.
##### Request Body
* `CountryName` is the name needed for the new country.
``` JSON
{
    "countryName": <country_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-country' \
--header 'Content-Type: application/json' \
--data '{
    "countryName": <country_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "countryId": <country_id_Integer>,
    "countryName": <country_name>
}
```

#### _Create City_
##### Description
This endpoint allows the ADMIN to create a new city.
##### Request Body
* `CityName` is the name needed for the new city.
* `CountryName` is the name needed for an existing country to which the city will be assigned.
``` JSON
{
    "cityName": <city_name>,
    "countryName": <country_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-city' \
--header 'Content-Type: application/json' \
--data '{
    "cityName": <city_name>,
    "countryName": <country_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "cityId": <city_id_Integer>,
    "cityName": <city_name>,
    "country": {
        "countryId": <country_id_Integer>,
        "countryName": <country_name>
    }
}
```

#### _Create Position_
##### Description
This endpoint allows the ADMIN to create a new position(e.g. IT Technician, Network Engineer).
##### Request Body
* `name` is the name needed for the new position.
``` JSON
{
    "name": <position_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-position' \
--header 'Content-Type: application/json' \
--data '{
    "name": <position_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "positionId": <position_id_Integer>,
    "name": <position_name>
}
```

#### _Create Role_
##### Description
This endpoint allows the ADMIN to create a new role(ROLE_ADMIN, ROLE_USER, ROLE_MANAGER).
##### Request Body
* `name` is the name needed for the new role.
``` JSON
{
    "name": <role_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-role' \
--header 'Content-Type: application/json' \
--data '{
    "name": <role_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "roleId": <role_id_Integer>,
    "name": <role_name>
}
```

#### _Create Status_
##### Description
This endpoint allows the ADMIN to create a new status(Rejected, Pending, Accepted).
##### Request Body
* `name` is the name needed for the new status.
``` JSON
{
    "name": <status_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-status' \
--header 'Content-Type: application/json' \
--data '{
    "name": <status_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "statusId": <status_id_Integer>,
    "name": <status_name>
}
```

#### _Create Skill Type_
##### Description
This endpoint allows the ADMIN to create a new skill type(Tech, Soft). 
##### Request Body
``` Json
{
    "name": <skill_type_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-skill-type' \
--header 'Content-Type: application/json' \
--data '{
    "name": <skill_type_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "skillTypeId": <skill_type_id_Integer>,
    "name": <skill_type_name>
}
```

#### _Create Skill_
##### Description
This endpoint allows the ADMIN to create a new skill (Java, JS, communication, teamwork etc.).
##### Request Body
``` Json
{
    "skillName": <skill_name>,
    "skillType": <skill_type_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-skill' \
--header 'Content-Type: application/json' \
--data '{
    "skillName": <skill_name>,
    "skillType": <skill_type_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "skillId": <skill_id>,
    "name": <skill_name>,
    "skillType": {
        "skillTypeId": <skill_type_id>,
        "name": <skill_type_name>
    }
}
```

#### _Create Language_
##### Description
This endpoint allows the ADMIN to create a new language.
##### Request Body
``` Json
{
    "languageName": <language_name>
}
```
##### Request
``` Curl
curl --location 'http://localhost:8080/admin/save-language' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiZW1haWwiOiJ2aWN0b3IucmV2ZW5vYy4wMEBnbWFpbC5jb20iLCJyb2xlIjoiUk9MRV9BRE1JTiIsImlhdCI6MTczMjcyNzExNSwiZXhwIjoxNzMyOTg2MzE1fQ.l6F-RJOEkvR2IOldJ3ISbSj3g_TkFaF6rqVnM9FNP8o' \
--data '{
    "languageName": <language_name>
}'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "languageId": <language_id>,
    "languageName": <language_name>
}
```

#### _Accept User_
##### Description
This endpoint allows the ADMIN to accept a Pending user with a specific role.
##### Request
* `<user_role>` - the user role must exist in the db, and it must start with "ROLE_"
``` Curl
curl --location --request PUT 'http://localhost:8080/admin/accept-user/<user_id>>/<user_role>'
```
##### Response
Upon execution, this endpoint returns a JSON response with the following structure:
``` JSON
{
    "userId": <user_id>,
    "avatar": <user_avatar>,
    "firstName": <user_first_name>,
    "lastName": <user_last_name>,
    "email": <user_email>,
    "password": <user_password_boolean>,
    "position": <user_position>,
    "seniority": <user_seniority>,
    "city": <user_city>,
    "languages": <user_languages>,
    "cv": <user_cv>,
    "project": <user_project>,
    "skillRating": <user_skill_rating>,
    "role": {
        "roleId": <role_id>,
        "name": <role_name>
    },
    "status": {
        "statusId": <status_id>,
        "name": <status_name>
    },
    "managerId": <manager_id>,
    "oauthToken": <oauthtoken>
}
```