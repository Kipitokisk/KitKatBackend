### **Manager Controller**
#### _Get Worker By Email_
##### Description
This endpoint returns information about a worker by its ID.
##### Request
````Curl
curl --location 'http://localhost:8080/manager/worker/{id}'
````
##### Response
Upon execution, the response is a JSON with information about requested worker
```JSON
{
    "id": "<int>",
    "name": "<string>",
    "surname": "<string>",
    "email": "<string>",
    "avatar": "<bit_array>",
    "seniority": "<string>",
    "role": "<string>",
    "languages": "<string_list>",
    "skills": "<string_list>",
    "city": "<string>",
    "cv": "<bit_array>",
    "project": "<string>"
}
```
* `avatar` is the users avatar as BitSet.
* `languages` is a string list of worker's languages
* `skills` is a string list of worker's skills
* `city` is a string (city, country)
* `cv` is a bit array
* `project` is a string of project name

#### _Get Projects_
##### Description
This endpoint returns all projects from database
##### Request
````Curl
curl --location 'http://localhost:8080/manager/worker/project'
````
##### Response
Upon execution, the response is a JSON with information about all projects
````Json
[
  {
    "projectId" : "<int>",
    "projectName" : "<string>",
    "managerId" : "<string>",
    "startDate" : "<LocalDateTime>",
    "finishDate" : "<LocalDateTime>",
    "status" : "<boolean>"
  }
]
````

#### _Set Project_
##### Description
This endpoint assigns a project to the given worker
##### Request Body
```Json
{
  "workerId"  : "<int>",
  "projectId" : "<int>"
}
```
##### Request
````Curl
curl --location 'http://localhost:8080/manager/worker/project' \
--header 'Content-Type: application/json' \
--data '{
    "workerId"  : <int>,
    "projectId" : <int>
}'
````
##### Response
Response in case of success -> status 200 OK

#### _Filter Users_
##### Description
This endpoint allows the manager to filter the search of all users.
##### Request
If there are more filters for each category, all names should be input with "," and no white spaces.
An empty filter will return all users.
```Curl
curl --location 'http://localhost:8080/manager/filter?position=%3Cposition_name%3E&seniority=%3Cseniorty_name%3E&country=%3Ccountry_name%3E&skill=%3Cskill_name%3E&languages=%3Clanguage_name%3E&roles=%3Crole_name%3E&page=%3Cpage_number%3E&size=%3Cresults_size%3E' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiZW1haWwiOiJ2aWN0b3IucmV2ZW5vYy4wMEBnbWFpbC5jb20iLCJyb2xlIjoiUk9MRV9NQU5BR0VSIiwiaWF0IjoxNzMyODE5OTkzLCJleHAiOjE3MzMwNzkxOTN9.zSxjzSC6biaPhc8ApXUKnJFK3GtCbdYalJdlCzcbiQs'
```
##### Response
```Json
{
    "content": [
        {
            "userId": 2,
            "avatar": null,
            "firstName": "Victor",
            "lastName": "Revenco",
            "email": "victor.revenoc.00@gmail.com",
            "password": {
                "empty": false
            },
            "position": {
                "positionId": 1,
                "name": "BackEnd Developer"
            },
            "seniority": null,
            "city": {
                "cityId": 1,
                "cityName": "Chisinau",
                "country": {
                    "countryId": 1,
                    "countryName": "Moldova"
                }
            },
            "languages": [
                {
                    "languageId": 1,
                    "languageName": "Romanian"
                },
                {
                    "languageId": 2,
                    "languageName": "English"
                },
                {
                    "languageId": 3,
                    "languageName": "French"
                },
                {
                    "languageId": 4,
                    "languageName": "German"
                }
            ],
            "cv": null,
            "project": null,
            "skillRating": [
                {
                    "skillRatingId": 1,
                    "skill": {
                        "skillId": 3,
                        "name": "Java",
                        "skillType": {
                            "skillTypeId": 2,
                            "name": "Tech"
                        }
                    },
                    "ratingSum": 5,
                    "nrOfReviews": 5
                },
                {
                    "skillRatingId": 2,
                    "skill": {
                        "skillId": 4,
                        "name": "JS",
                        "skillType": {
                            "skillTypeId": 2,
                            "name": "Tech"
                        }
                    },
                    "ratingSum": 3,
                    "nrOfReviews": 2
                }
            ],
            "role": {
                "roleId": 3,
                "name": "ROLE_MANAGER"
            },
            "status": {
                "statusId": 2,
                "name": "PENDING"
            },
            "managerId": null,
            "oauthToken": null
        }
    ],
    "pageable": {
        "pageNumber": 3,
        "pageSize": 1,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "offset": 3,
        "unpaged": false,
        "paged": true
    },
    "totalElements": 4,
    "totalPages": 4,
    "last": true,
    "size": 1,
    "number": 3,
    "sort": {
        "empty": true,
        "unsorted": true,
        "sorted": false
    },
    "numberOfElements": 1,
    "first": false,
    "empty": false
}
```

#### _Find Count of Users without Project_
##### Description
This endpoint returns the count of users without project.
##### Request
```Curl
curl --location 'http://localhost:8080/manager/without-project'
```
##### Response
```Json
{
  "count": 1
}
```

#### _Find Count of Users without Project by Country_
##### Description
This endpoint returns the count of users of a country without project.
##### Request
```Curl
curl --location 'http://localhost:8080/manager/without-project/<country_name>'
```
##### Response
```Json
{
  "count": 1
}
```

#### _Create Project_
##### Description
This endpoint allows a MANAGER to create a project.
##### Request Body
* `finishDate` - this date should be format "yyyy-MM-ddTHH:mm:ss"
```Json
{
  "projectName": "Kitkat",
  "managerId": 2,
  "finishDate": "2024-01-01T22:22:22"
} 
```
##### Request
```Curl
curl --location 'http://localhost:8080/manager/save-project' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiZW1haWwiOiJ2aWN0b3IucmV2ZW5vYy4wMEBnbWFpbC5jb20iLCJyb2xlIjoiUk9MRV9NQU5BR0VSIiwiaWF0IjoxNzMyODE5OTkzLCJleHAiOjE3MzMwNzkxOTN9.zSxjzSC6biaPhc8ApXUKnJFK3GtCbdYalJdlCzcbiQs' \
--header 'Cookie: JSESSIONID=D32931B7950AB70ED9CC50EE7F1A782B' \
--data '{
    "projectName": "Kitkat",
    "managerId": 2,
    "finishDate": "2024-01-01T22:22:22"
}'
```
##### Response
```Json
{
    "projectId": 1,
    "projectName": "Kitkat",
    "manager": {
        "userId": 2,
        "avatar": null,
        "firstName": null,
        "lastName": null,
        "email": "victor.revenoc.00@gmail.com",
        "password": {
            "empty": false
        },
        "position": null,
        "seniority": null,
        "city": null,
        "languages": [],
        "cv": null,
        "project": null,
        "skillRating": [
            {
                "skillRatingId": 1,
                "skill": {
                    "skillId": 3,
                    "name": "Java",
                    "skillType": {
                        "skillTypeId": 2,
                        "name": "Tech"
                    }
                },
                "ratingSum": 5,
                "nrOfReviews": 5
            },
            {
                "skillRatingId": 2,
                "skill": {
                    "skillId": 4,
                    "name": "JS",
                    "skillType": {
                        "skillTypeId": 2,
                        "name": "Tech"
                    }
                },
                "ratingSum": 3,
                "nrOfReviews": 2
            }
        ],
        "role": {
            "roleId": 3,
            "name": "ROLE_MANAGER"
        },
        "status": {
            "statusId": 2,
            "name": "PENDING"
        },
        "managerId": null,
        "oauthToken": null
    },
    "startDate": "2024-11-28T20:07:55.009480382",
    "finishDate": "2024-01-01T22:22:22",
    "status": true
}
```
