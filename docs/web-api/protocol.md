# YAAM Web Protocol

## Funtionality lacking documentation in this document
1.  There is no mechanism that allows the client to retrieve a list of known Users
2. There is no mechanism that allows the client to retrieve a list containing the projects they are a member of
3. There is no documented mechanism that allows the client to retrieve the attributes of a project (such as its name, description, tags, owner, etc.) other than the pursuits that are part of that project
4. There is no mechanism that allows the client to modify a project
5. There is no mechanism that allows the client to update a projects attributes (there is a way to update tasks and sprints attributes)
6. There is no mechanism that allows the client to delete a project, sprint, or task
7. There is no mechanism for a user to change their account's password or display name (perhaps changing user attributes like username, password, displayname, and SSN should be consolidated under one endpoint?)
8. Most importantly none of the webapi endpoints specify what data they expect to receive.

## Authentication
### /auth
#### Inbound
#### Outbound

## User
### /user/create
#### Inbound
#### Outbound
### /user/changeUsername
#### Inbound
#### Outbound

## Project
### /project/create
#### Inbound
#### Outbound
### /project/{project UUID}/task/create
#### Inbound
#### Outbound
### /project/{project UUID}/sprint/create
#### Inbound
#### Outbound


### /project/{project UUID}/pursuits
#### Inbound
#### Outbound

### /project/{project UUID}/task/{task uuid}
#### Inbound
#### Outbound
### /project/{project UUID}/sprint/{sprint uuid}
#### Inbound
#### Outbound

### /project/{project UUID}/task/{task uuid}/update
#### Inbound
#### Outbound
### /project/{project UUID}/sprint/{sprint uuid}/update
#### Inbound
#### Outbound
