### **Manager Controller**
#### _Workers_
##### Description
This endpoint gets all users with "Worker" as role from database.
##### Request
````Curl
curl --location 'http://localhost:8080/manager/workers'
````
##### Response
Upon execution, the response is a JSON with all users with role "Worker"
````JSON
[
    {
        "id" : "<id>",
        "name": "<name>",
        "surname": "<surname>",
        "email": "<email>",
        "avatar": "<hero_image>",
        "seniority": "<seniority>",
        "role": "<position>",
        "languages": "<languages>",
        "skills": "<skills>",
        "city" : "<city>"
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
    "id": "<id>",
    "name": "<name>",
    "surname": "<surname>",
    "email": "<email>",
    "avatar": "<avatar>",
    "seniority": "<seniority>",
    "role": "<position>",
    "languages": "<languages>",
    "skills": "<skills>",
    "city": "<city>",
    "cv": "<cv>",
    "project": "<project_name>"
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
curl --location 'http://localhost:8080/manager/worker/get-projects'
````
##### Response
Upon execution, the response is a JSON with information about all projects
````Json
[
  {
    "projectId" : "<project_id>",
    "projectName" : "<project_name>",
    "manager" : "<manager>",
    "startDate" : "<start_date>",
    "finishDate" : "<finish_date>",
    "status" : "<status>"
  }
]
````

#### _Set Project_
##### Description
This endpoint assigns a project to the given worker
##### Request Body
```Json
{
  "workerId"  : "<id>",
  "projectName" : "<project_name>"
}
```
##### Request
````Curl
curl --location 'http://localhost:8080/manager/worker/set-project' \
--header 'Content-Type: application/json' \
--data '{
    "workerId"  : <id>,
    "projectName" : <project_name>
}'
````
##### Response
Response in case of success -> status 200 OK
