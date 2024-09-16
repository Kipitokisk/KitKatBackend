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
    "countryId": <country_id>,
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
    "cityId": <city_id>,
    "cityName": <city_name>,
    "country": {
        "countryId": <country_id>,
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
    "positionId": <position_id>,
    "name": <position_name>
}
```

#### _Create Role_
##### Description
This endpoint allows the ADMIN to create a new role(Admin, Worker, Manager).
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
    "roleId": <role_id>,
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
    "statusId": <status_id>,
    "name": <status_name>
}
```