# HeartBank - Private API for Internal Web Application for Banking Operations Management

## Document information
* Document version: `1.0.0`
* API version `v1`
* Last updated: `07.08.2025`
* Last update by: `Sabin Tarba, sabintarba01@gmail.com`

<div style="page-break-after: always;"></div>

## Table of content

| Title     | - | Page |
|---------------|----------------------------------|---------------------------------------------|
| **Infrastructure** | --------------------------------------------------------------- | 3
| **API Reference** | --------------------------------------------------------------- | 4
|      **I. Audit Module** | --------------------------------------------------------------- | 4
|      **II. Error handling & error codes** | --------------------------------------------------------------- | 7

<div style="page-break-after: always;"></div>

## Infrastructure:
* Database: `Oracle 23ai`
* Backend: `Spring Boot` & `Java 17`
* Container: `Docker` (one image for database, one image for Spring Boot application)

<div style="page-break-after: always;"></div>

## API Reference

### I. Audit Module

1. `GET /api/v1/audit/logs?page={number}&size={number}&sort=timestampCreated,{asc|desc}`

* REQUEST

| Attribute     | Example                          | Description                                 | Data type | Required |
|---------------|----------------------------------|---------------------------------------------| --------- | -------- |
| `No body required` | - | - | - | = |

* QUERY PARAMETERS

| Parameter     | Example                          | Description                                 | Required |
|---------------|----------------------------------|---------------------------------------------| -------- |
| `page` | 0 | The page of audit logs | No (default: 0 - first page) |
| `size` | 15 | The number of items on the page | No (default: 10) |
| `sort` | timestampCreated,desc | Specify how rows should be sorted  | No (default: timestmapCreated,desc) |

* RESPONSE

| Attribute     | Example                          | Description                                 | Data type |
|---------------|----------------------------------|---------------------------------------------| --------- |
| `data` | [...] | It is always a list containing JSON objects as described below | JSON array of JSON objects |
| `data.id` | 5 | Audit log ID | Number(19) |
| `data.audiType` | ACCOUNT | Audit type, values: ACCOUNT | String(30) |
| `data.auditAction` | HOME | Audit action, description of the action | String(30) |
| `data.timestampCreated` | 2025-08-07T16:51:09.307254 | Local server timestamp of the audit in YYYY-MM-DDTHH:MM:SS.ssssss (ISO 8601 format) | Local ISO timestamp |
| `data.params` | [...] | Extra additional parameters in key value format (if exists, otherwise empty JSON array) | JSON array of JSON objects |
| `data.params.timestampCreated` | 2025-08-07T16:51:09.307254 | Local server timestamp of param created in YYYY-MM-DDTHH:MM:SS.ssssss (ISO 8601 format) | Local ISO timestamp |
| `data.params.key` | kycVerifid | Key name | String(100) |
| `data.params.value` | true | Key value | String(250) |
| `pagination` | {...} | Pagination object | JSON object |
| `pagination.page` | 0 | Current page | Number |
| `pagination.size` | 10 | Current page size | Number |
| `pagination.totalPages` | 2 | Number of total pages | Number |
| `pagination.totalElements` | 13 | Number of total items | Number |
| `pagination.hasNext` | true | True if has more pages | Boolean (true / false) |
| `pagination.hasPrevious` | false | True if is not first page | Boolean (true / false) |

* RESPONSE - EXAMPLE

```json
{
    "data": [
        {
            "id": 7,
            "auditType": "ACCOUNT",
            "auditAction": "HOME",
            "timestampCreated": "2025-08-07T16:51:09.801093",
            "params": []
        },
        {
            "id": 6,
            "auditType": "ACCOUNT",
            "auditAction": "HOME",
            "timestampCreated": "2025-08-07T16:51:09.307254",
            "params": []
        },
        {
            "id": 5,
            "auditType": "ACCOUNT",
            "auditAction": "HOME",
            "timestampCreated": "2025-08-07T16:51:08.763348",
            "params": [
                {
                    "timestampCreated": "2025-08-07T17:52:00.586244",
                    "key": "kycVerified",
                    "value": "false"
                },
                {
                    "timestampCreated": "2025-08-07T17:52:10.986278",
                    "key": "verified",
                    "value": "true"
                }
            ]
        },
        {
            "id": 4,
            "auditType": "ACCOUNT",
            "auditAction": "HOME",
            "timestampCreated": "2025-08-07T16:51:08.190683",
            "params": []
        },
        {
            "id": 3,
            "auditType": "ACCOUNT",
            "auditAction": "HOME",
            "timestampCreated": "2025-08-07T16:51:07.196704",
            "params": []
        },
        {
            "id": 2,
            "auditType": "ACCOUNT",
            "auditAction": "HOME",
            "timestampCreated": "2025-08-07T16:51:03.022953",
            "params": [
                {
                    "timestampCreated": "2025-08-07T17:52:17.597008",
                    "key": "userAge",
                    "value": "35"
                }
            ]
        }
    ],
    "pagination": {
        "page": 0,
        "size": 10,
        "totalPages": 1,
        "totalElements": 6,
        "hasNext": false,
        "hasPrevious": false
    }
}
```

<div style="page-break-after: always;"></div>

### II. Error handling & error codes

* Error codes

| Error code     | Description                          |  HTTP status code  |
|---------------|----------------------------------|---------------------------------------------|
| `ERR000` | Internal server error | 500 |
| `ERR001` | Resource not found | 404 |
| `ERR002` | Unauthorized access | 401  |
| `ERR003` | Argument validation error | 400  |

* RESPONSE

| Attribute     | Example                          | Description                                 | Data type |
|---------------|----------------------------------|---------------------------------------------| --------- |
| `errorMessage` | Resource not found | Error message | String | 
| `errorCode` | ERR001 | Error code described above | String |
| `timestamp` | 2025-08-07T16:51:09.307254 | Local server timestamp in YYYY-MM-DDTHH:MM:SS.ssssss (ISO 8601 format) | Local ISO timestamp |
| `status` | 404 | HTTP status code | Number |


* RESPONSE - EXAMPLE

```json
{
    "errorMessage": "Resource not found",
    "errorCode": "ERR001",
    "timestamp": "2025-08-07T18:10:32.68212348",
    "status": 404
}
```