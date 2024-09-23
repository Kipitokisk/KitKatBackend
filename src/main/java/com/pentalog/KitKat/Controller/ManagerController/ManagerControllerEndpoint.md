### **Manager Controller**
#### _Workers_
##### Description
This endpoint gets all users with "Worker" as role from database.
##### Request
````Curl
curl --location 'http://localhost:8080/manager/worker'
````
##### Response
Upon execution, the response is a JSON with all users with role "Worker"
````JSON
[
    {
        "id" : "<int>",
        "name": "<string>",
        "surname": "<string>",
        "email": "<string>",
        "avatar": "<bit_array>",
        "seniority": "<string>",
        "role": "<string>",
        "languages": "<string_array>",
        "skills": "<string_array>",
        "city" : "<string>"
    }
]
````
* `avatar` is the users avatar as BitSet.
* `languages` is a string list of worker's languages
* `skills` is a string list of worker's skills
* `city` is a string "country, city"

#### _Worker {id}_
##### Description
This endpoint returns information about a worker by its ID
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
    "languages": "<string_array>",
    "skills": "<string_array>",
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
    "manager" : "<string>",
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
  "projectName" : "<string>"
}
```
##### Request
````Curl
curl --location 'http://localhost:8080/manager/worker/project' \
--header 'Content-Type: application/json' \
--data '{
    "workerId"  : <id>,
    "projectName" : <project_name>
}'
````
##### Response
Response in case of success -> status 200 OK
