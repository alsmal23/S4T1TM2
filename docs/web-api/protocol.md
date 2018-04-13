# YAAM Web Protocol
@paul-mchugh Needs Review

For now put User uuid in Authorization field of http header

## Funtionality lacking documentation in this document
- [x]  There is no mechanism that allows the client to retrieve a list of known Users
- [x] There is no mechanism that allows the client to retrieve a list containing the projects they are a member of
3. There is no documented mechanism that allows the client to retrieve the attributes of a project (such as its name, description, tags, owner, etc.) other than the pursuits that are part of that project
4. There is no mechanism that allows the client to modify a project
5. There is no mechanism that allows the client to update a projects attributes (there is a way to update tasks and sprints attributes)
6. There is no mechanism that allows the client to delete a project, sprint, or task
7. There is no mechanism for a user to change their account's password or display name (perhaps changing user attributes like username, password, displayname, and SSN should be consolidated under one endpoint?)
- [x] Most importantly none of the webapi endpoints specify what data they expect to receive.

## Authentication
### /auth
Inbound  
Outbound  

## User
### /user/create
__Inbound:__  POST JsonObject. fields: username, hashword?, display_name(optional),  
Outbound  report success failure? HOW?  
### /user/changeUsername  
Inbound  POST JsonObject. fields: display_name  
Outbound    

### /user/projects
Inbound GET  
Outbound JsonArray of project uuid's the user has some permissions on. Fields: uuid  

## Project
### /project/create
Inbound  POST JsonObject. fields: creator_uuid?, name  
Outbound  
### /project/{project UUID}/task/create
Inbound  POST JsonObject. fields: name, description (optional), parent_uuid(optional), size_uuid(optional), assignee_uuid(optional)  
Outbound  
### /project/{project UUID}/sprint/create
Inbound  POST JsonObject. fields: name, description(optional), size(optional), due_date(optional)  
Outbound  


### /project/{project UUID}/pursuits
Inbound   GET  
Outbound  JsonArray of JsonObjects. fields: uuid, name, type, description

### /project/{project UUID}/task/{task uuid}  
Inbound  GET  
Outbound  JsonObject. fields: parent_uuid, size_uuid, assignee_uuid
### /project/{project UUID}/sprint/{sprint uuid}
Inbound  GET  
Outbound  JsonObject. fields: Text size, Double due_date

### /project/{project UUID}/task/{task uuid}/update
Inbound  POST JsonObject. fields: any altered fields of (parent_uuid, size_uuid, assignee_uuid, name, description)  
Outbound  indicate success/failure?  
### /project/{project UUID}/sprint/{sprint uuid}/update
Inbound  POST JsonObject. fields: any altered fields of (name, description,size?, due_date )  
Outbound  indicate success/failure?  

### /project/{project UUID}
Inbound GET  
Outbound JsonObject. fields: name.

### /project/{project UUID}/user
Inbound: GET  
Outbound: JsonArray of JsonObjects. fields: uuid, display_name
