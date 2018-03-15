# Yet Another Agile Manager (YAAM)  
  
## Introduction
  The agile development process naturally tends to be both sporadic and volatile during the iterative design and implementation process. Project organization and group communication play critical roles in attempting to streamline the development process. The effective goal of the YAAM project is to create a task management software that can bridge the gap between development and organization for small development teams. The software is intended to allow managers and developers alike to organize project tasks and seamlessly track the time spent on completing them. Furthermore, the software will aggregate project task data into a complex statistical summary report for supervisors.
  
## Product Reviews  (@lihaven TODO → Review, make consistent formatting?)
### [Agilean](https://agilean.in/) 
Agilean is an online SaaS agile development team management system. Agilean comes in two flavors. The Kanban flavor and the scrum flavor. This changes how task progress is managed and viewed. Kanban supports a more continuous work flow, while scrum is more of a sprint style work flow.  We chose to review the Scrum flavor as that seemed to more closely correspond to sprints in Agile Development.  

##### Task Management  
Agilean allows many users to view the progress of the many projects. Users have roles for each project, Users are developers on some projects, but may be managers/team leads on other projects.  
On the requirements tab managers create Epics. Epics are assigned to projects. User Stories are assigned to epics. Tasks are assigned to user stories. And sub-tasks can be associated with tasks. Tasks are assigned story points.Tasks can be tagged with custom tags, and colored from a set of predefined colors, teams are able to use these in indicate whatever they need. A task may be tagged however a team finds useful.   

Managers then create Sprints and Releases, and assign tasks to them.  
- Projects → Epics → User Stories → Tasks (→ Sub-tasks)  
- Projects →  Releases → Sprints → Tasks
- Projects →  Impedements
   
##### Reporting  
Agilean offers many views of the project. The Requirements view displays all of the Epics, User Stories and Tasks. The board view displays all of the tasks in the current sprint on a swim lane board with lanes representing various levels of completion, from Backlog to Done. Agilean also offers a comprehensive reporting view, showing how many tasks are assigned to each developer, how many tasks are in each stage of completion, a burndown chart, among others. 
  
  
### [RescueTime](https://www.rescuetime.com/)   
##### Overview
RescueTime is desktop time-management app intended to help users analyze their daily program usage habits. Although not necessarily intended for tracking time for projects or its various components, it is especially apt at recording time spent on relevant program usage data. The desktop application has minimal UI and serves as principally a usage recorder; all statistical aggregate data is viewed on their website's dashboard.

##### Time Tracking
RescueTime uses their desktop application to record time usage on all programs based on process image names and mouse and keystroke interaction. All program usage data is sent to their remote server under the user's registered account; since data is remotely stored, one user may use RescueTime from various computers and all their data is synchronized.

##### Statistical Aggregation
RescueTime's principal selling point is its complex interactive and exhaustive statistical aggregation breakdown. Its dashboard permits viewing both derived and raw data interactively. Its breakdown features apt usage of bar charts, pie charts, and graph statistics that can be viewed for a specific day, week, month, or year. Additionally, it categorizes various softwares into functionality groups (e.g. software development, communication & scheduling, utilities, entertainment).

 
### [Timesheet](https://www.timesheet.io/)  
>This section definitely not done yet, but wanted to get some of my research visable. I plan to make this into a more compact and presentible form.

#### Overview 
Timesheet is and individual user time tracking solution with a focus on tracking contract work. Although it includes a lot of features that probabably won't want to consider for YAAM (including, but not limited to billing and expense tracking because the assumption is that YAAMs users are salaried), it is primarly a time and completed task tracking applciation. This makes it relelevant our research because it's conceptually very similar to out first TM application in class, but with a GUI and some critical features added, primarily projects, tags, and GUIs on web and mobile. 

The general user flow in Timesheets is for a user to create a project, maybe a tag or two, and then start tracking tasks with the timer, once tasks are stopped they will ask for tags and a description. Note the mobile app is free, but the web app is not, therefore the mobile app with timer is the most common use case.

#### Structures flow
<table>
  <tr>
    <td colspan="6"><center>Projects hold</center></td>
  </tr>
  <tr>
    <td colspan="6"><center>Tasks hold</center></td>
  </tr>
  <tr>
    <td>Task Data</td>
    <td>Tags</td>
    <td>Breaks</td>
    <td>Expenses</td>
    <td>Notes</td>
    <td>Feelings</td>
  </tr>
</table>

#### Details on Major Features and Functions
Timer
* Start: Starts a timer and displays it in notification bar with simple options
* Pause: Pauses timer, tasks track paused time. Unpause brings up a simple page to save notes about the break or change the time of the break.
* Stop: Stops the task. Brings up a simple page to save notes about the task or change any of the feilds on the task.
-
Project
* Project fields: Name, Employer/Client, Description, Location, Rate, Color, Archive (yes/no)

Tasks
Two different task views calendar or list on web app, list only on mobile app.
* Task Data: Project, Start Date & Time, End Date & Time, Tags (many), Task Type (Task, Milage, Call [predefined, not editable]), Billable (yes/no), Paid (yes/no)
* Feeling Options → :D     :|     :/    :( ...This features seems kind of out of place in the app, I suppose it's a quality of job/life measure. No compiling or statistics on this field.
* Notes section: A Large-ish text field where notes can be added after the task has been completed (either at manual creation or when the timer is stopped).
* Expenses Fields: Date & time incurred, Amount, Description, Paid Status <yes/no>
* Break Fields (pause): Start/Stop & Duration, Description

Tags
* Tag fields: Name and color

Statistics
* Reporting Features: Date selections (several quick access pre-defined periods, as well as custom periods), Time Spent, Break Time, Salary, Progress (since it doesn't track goals, this is more like a sum of time spent day to day), Average Daily Working Hours, Time Spent Per Tag (a pie chart and hour break down text list), Projects (a pie chart and hour break down text 


## Project Overview  
YAAM is a client-server application with all data stored on a private server. Customers will host a YAAM server and make accounts for their team on that server. They will run a client side desktop application in order to access that server. The client will observe the developer and issue smart reminders to log work done when the developer uses IDEs for example. This will allow us to bring  Rescue Times tracking ability, and TimeSheets intentional tracking with Agilean's application domain specific reporting, and project progress tracking.   
  
With automatic reminders tracking data will be more reliable. This will enable project leads to understand their teams velocity and generate more accurate predictions about future productivity. With online storage, YAAM will provide project managers with powerful options for communicating with their customers, and permissions will allow those customers to only see what information matters to them, without all of the technical details. 

## Project Architecture  
The project architecture for our software incorporates various ideas from both our team and our product reviews. Our architecture will make use a client-server approach to maximize team collaboration and accessibility. All software implementations (client and server) will be written in Java 8, with the exception of the server's persistent data storage. The client's role is to provide the means for inputting and reviewing project data. The server's role is to serve as a database endpoint to synchronize all relevant project data to an entire development team.

#### Client
The only software client will be a JavaFX-driven GUI desktop application. Users will be able to create, edit, and interact with organizations, projects, and tasks; behind the scenes, all modification data will be sent to the server to handle and store. The desktop application will serve to organize tasks, track time spent on tasks, and provide a statistical aggregation breakdown for tasks. 

The following third-party softwares will be used in the client architecture:
- To provide a GUI with rich UI design:
  - **[JavaFX](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html)**: "JavaFX is the next step in the evolution of Java as a rich client platform. It is designed to provide a lightweight, hardware-accelerated Java UI platform for enterprise business applications."
  - **[JFoenix](https://github.com/jfoenixadmin/JFoenix)**: "JFoenix is an open source Java library, that implements Google Material Design using Java components"
- To track time spent working on specific programs for tasks:
  - **[Java Native Access](https://github.com/java-native-access/jna) (JNA)**: "JNA provides Java programs easy access to native shared libraries without writing anything but Java code - no JNI or native code is required." (e.g. access platform specific libraries to track program interaction)
  - **[system-hook](https://github.com/kristian/system-hook)**: "Java (low-level) System Hook provides a very light-weight global keyboard and mouse listener for Java."

#### Server
The server software will serve to store and retrieve all data related to a users organizations, projects, and tasks. Clients will send appropriate user data to the server via a web server with a HTTP API endpoint that will process and store appropriate information. The web server will make use of MySQL to store all relevant data related to users, organizations, projects, and tasks.

The following third-party softwares will be used in the server architecture:
- To provide a HTTP API endpoint:
  - **[Embedded Jetty](https://www.eclipse.org/jetty/)**: Eclipse Jetty provides a Web server and javax.servlet container, plus support for HTTP/2, WebSocket, OSGi, JMX, JNDI, JAAS and many other integrations."
    - **[Spark](http://sparkjava.com/)**: "A micro framework for creating web applications in Kotlin and Java 8 with minimal effort." Spark (which runs on Jetty) will be used to mitigate the overhead of programmatically creating a Jetty server for the HTTP API.
  - **[JSON](https://github.com/stleary/JSON-java)**: "JSON is a light-weight, language independent, data interchange format." JSON will be used for data payloads for receiving and sending data to clients.
- To persistently store data:
  - **[MySQL](https://www.mysql.com/)**: MySQL is a open source, relational SQL database management system. 
  - **[HikaryCP](https://github.com/brettwooldridge/HikariCP)**: "HikariCP is a 'zero-overhead' production ready JDBC connection pool"
  
  
## Requirements 
|REQ-###|Description|
|------|-----------|
|**REQ-1**|The cloud server application shall store and provide a record of projects, sprints, tasks, and their associated attributes.|
|**REQ-2**|The desktop client application shall be the GUI access point for retrieving and modifying data (e.g. retrieve and modify the record of sprints, tasks, and their associated attributes) on the cloud server.|
|**REQ-3**|Users shall be able to register an account on the cloud server.|
|**REQ-4**|Users shall be able to create a new project.|
|**REQ-5**|Users, as a the project creator, shall be able to invite additional registered users to view (and potentially modify) a project.|
|**REQ-6**|Users who are project creators shall be able to set read, write, or other varying permissions for invited/added users.|
|**REQ-7**|Users with appropriate permissions shall be able to create a sprint or task. |
|**REQ-8**|Users with appropriate permissions shall be able to specify attributes for sprints and tasks, such as descriptions, assignee(s), task size, status, due dates, and varying other properties.|
|**REQ-9**|Users with the appropriate permissions shall be able to define their own attributes on a per-project basis. For example: customizable task sizes or statuses.|
|**REQ-10**|Users with the appropriate permissions shall be able to log the times when they started and stopped working on a task; they shall also be able to edit their own time task time histories to rectify clerical errors.|
|**REQ-11**|Users shall be able to view work summaries for a specific project, sprint, task, or user.|velopers computer and shall use heuristics to determine if the developer is working on a task assigned to them.  If it makes such a determination and the developer **has not** logged work as being in progress the application shall send the a notification that will remind them to log work as being in progress.|


## Use Cases  (@paul-mchugh TODO → Get use cases tasks list, assign out)
Your requirements and previous sections should lead to your use cases. How will users use the system? What are the types of users?  This sections should include detailed use cases as well as an accountability matrix that ties your requirements to your use cases.

__Actors__
Anyone - Genearlly meaning anyone or thing acting as a human person
RegUser - Genearlly meaning anyone who has registered and has a valid user account
Programmer - Generally meaning the user with the lowest permissions for a project
Manager - Generally meanting the user with a high level of permissions for a project
Server - The data warehouse manager
Client - The customer service associate

__Derived Use Cases From Requirements__

|Actor     |Actor's Goal                                                                            |Use Case Name         |
|----------|----------------------------------------------------------------------------------------|----------------------|
|RegUser   |Will be able to create projects and have them saved on the server.                      |CreateProj(UC1)       |
|Manager   |Will be able to create sprints and have them saved on the server.                       |CreateSprints(UC2)    |
|Manager   |Will be able to create tasks and have them saved on the server.                         |CreateTasks(UC3)      |
|Manager   |Will be able to create and modify task attributes and have them saved on the server.    |ModAttribs(UC4)       |
|Manager   |Will be able to move a task from one sprint (or backlog) to another.                    |MoveTasks(UC5)        |
|Manager   |Will be able to create a sprint with a date.                                            |SprintDueDates(UC6)   |
|Manager   |Will be able to create a sprint with no due date.                                       |SprintNoDueDates(UC7) |
|Manager   |Will be able to give a sprint a name                                                    |SprintNames(UC8)      |
|Programmer|Will be able to create tasks and have them saved on the server.                         |CreateTasks(UC3)      |
|Programmer|Will be able to create and modify task attributes and have them saved on the server.    |ModAttribs(UC4)       |
|Programmer|Will be able to move a task from one sprint (or backlog) to another.                    |MoveTasks(UC5)        |
|Anyone    |Should be able to download and open the desktop client.                                 |DownloadClient(UC9)   |
|Anyone    |Should be able to register a new account through the __[desktop client/website, not sure what the registration flow should be]__ with an email and password __[or what required info is needed]__.		       |RegAccout(UC10)       |
|RegUser   |Should be able to open the desktop client and log into their account.		    |OpenDsktpClient(UC11) |
|RegUser   |Should be able to use a GUI to interact with project data through their desktop client. |UseGUI(UC12)          |
|RegUser   |Should have the permissions of a manager on a new project they create.                  |PermsNewProj(UC13)    |
|Manager   |Should be able to invite additional register users to access a project                  |InviteToProj(UC14)    |
|RegUser   |When invited to a new project they should have progammer like permissions by default.   |DefaultProjPerms(UC15)|
|RegUser   |Should be able to see notifications about invitations to projects.                      |InviteNotify(UC16)    |
|Manager   |Should be able to manager permissions and access of other users on a project.           |MngProjUsers(UC17)    |
|Manager   |Will be able to customize task sizes as project parameters.				    |DefineSizes(UC18)     |
|Manager   |Will be able to set a task size from one of the task size project parameters.	    |SetTaskSize(UC19)     |
|Programmer|Will be able to set a task size from one of the task size project parameters.	    |SetTaskSize(UC19)     |
|Manager   |Will be able to customize tags as project parameters.				    |DefineTags(UC20)      |
|Manager   |Will be able to set a task tags from one of the tag project parameters.		    |SetTaskTags(UC21)     |
|Programmer|Will be able to set a task tags from one of the tag project parameters.		    |SetTaskTags(UC21)     |
|Manager   |Start work on a task.								    |StartTask(UC22)     |
|Manager   |Stop work on a task.								    |StopTask(UC23)        |
|Manager   |Edit start and stop times on a task.						    |EditTaskTimes(UC24)   |
|Manager   |Add start and stop time on a task in the even they forgot to log the task entirely.	    |AddTaskTimes(UC25)    |
|Programmer|Start work on a task.								    |StartTask(UC22)       |
|Programmer|Stop work on a task.								    |StopTask(UC23)        |
|Programmer|Edit start and stop times on a task.						    |EditTaskTimes(UC24)   |
|Programmer|Add start and stop time on a task in the even they forgot to log the task entirely.	    |AddTaskTimes(UC25)    |
|Manager   |View statistical summaries page.							    |ViewStats(UC26)       |
|Manager   |Manage view using filters with various project, sprint, and task attributes.	    |FilterStats(UC27)     |
|Programmer|View statistical summaries page.							    |ViewStats(UC26)       |
|Programmer|Manage view using filters with various project, sprint, and task attributes.	    |FilterStats(UC27)     |

__Accountability Matrix__ (Assuming this means Tracability Matrix?)
There are more use cases than requirements, so I turned the table from what is in the  

|Req't|REQ1 |REQ2 |REQ3 |REQ4 |REQ5 |REQ6 |REQ7 |REQ8 |REQ9|REQ10|REQ11|REQ12|Max PW|Total PW|
|----:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|---|-------|
|PW   |     |     |     |     |     |     |     |     |     |     |     |     |   |    |
|UC1  |  x  |     |     |     |     |     |     |     |     |     |     |     |   |    |
|UC2  | x   |     |     |   x |     |     |    |    |    |     |     |     |      |        |
|UC3  |   x  |     |  x  |  x  |     |     |    |    |    |     |     |     |      |        |
|UC4  | x   |  x  |  x  |     |     |     |    |    |    |     |     |     |      |        |
|UC5  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC6  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC7  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC8  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC9  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC10 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC11 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC12 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC13 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC14 |    |      |     |     |     |    |    |    |    |     |     |     |      |        |
|UC15 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC16 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC17 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC18 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC19 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC20 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC21 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|Uc22 | x   |     |     |     |  x  |    |    |    |    |     |     |     |      |        |
|UC23 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC24 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC25 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC26 |     |  x  |     |     |     |    |    |    |    |     |  x  |     |      |        |
|UC27 |     |  x  |     |     |     |    |    |    |    |     |  x  |     |      |        |
---
__Detailed Use Cases__
(@lihaven TODO → make a detailed use case template)

|UC-1            | Create Account|
|--------------------:|--------------|
|Related Requirements:|REQ-1|
|Initiating Actor:|Anyone|
|Actor's Goals|Create an account on the server|
|Participating Actors|None|
|Preconditions|Actor must not already have an account on the server|
|Postconditions|User Account information stored by the server|
##### Flow of Events
 1. → User: selects create account function
 2. ← System: Displays a form to user
 3. User: Fills out Form
 4. → User: Submits Form
 5. ← System: 
	 * (a) Stores account information 
	 * (b) signals completions
---
##### Extensions   
  

5(b).
1. ← System: 
	* (a) signals that account information is incomplete/not unique
	* (b) returns form to user  
2.  return to step 3.

---
|UC-2            | Create Project|
|--------------------:|--------------|
|Related Requirements:|REQ-1, REQ-4|
|Initiating Actor:|Regular User|
|Actor's Goals|Create an Empty Project|
|Participating Actors|None|
|Preconditions|User must have an account|
|Postconditions|User is manager within the new Project|
##### Flow of Events
1. → User: Selects Create Project
2. ← System: Displays Form to User
3. User: Completes Form
4. → User: Submits Form
5. ← System:
	* (a) Creates Project 
	* (b) Signals to User Project Created
---
##### Extensions

5(b).
1. ← System: 
	 * (a) signals that account information is incomplete/not unique
	* (b) returns form to user  
3.  Return to 3.

---
|UC-3            | Create Sprint|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-3,REQ-4|
|Initiating Actor:|Manager|
|Actor's Goals|Create a Sprint within a Project|
|Participating Actors|None|
|Preconditions|A project must already exist|
|Postconditions|A sprint is created within a project|
##### Flow of Events
1. → User: selects create Sprint
2.  System: Checks if User has permissions to create Sprint
3. ← System: Sends form to Manager 
4. Manger: completes required fields 
5. →Manger: submits form
6. System: Creates Sprint
##### Extensions
3a. 
1. System Checks if User has permissions to create sprint
2. ←System: indicates that Sprint cannot be created

6a.
1. ←System returns incomplete or non-unique form
2.  →User submits completed form


---
|UC-4            | Modify task attributes|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-2,REQ-3|
|Initiating Actor:|Programmer|
|Actor's Goals|Set the size,due date, tags, of a task|
|Participating Actors|none|
|Preconditions|a task must exist|
|Postconditions|task attributes will be updated|
##### Flow of Events
1. →User: selects a task
2. ←System: displays task detailed view
3. →User: 
	* (a) selects attribute to change
	* (b) selects new value for attribute
4. System: updates value of task attribute

---
|UC-5            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-6            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-7            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-8            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-9            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-10            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-11            | EditProject|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:|Manager|
|Actor's Goals|To change properties global to a project such as the tags which are available to tasks.|
|Participating Actors|None|
|Preconditions|The selected project exists.|
|Postconditions|The selected project has the properties assigned by the manager.|
##### Flow of Events
1. -> Manager selects edit project properties.
2. Server validates the user is a manager.
3. <- Client displays edit project menu.
4. -> Manager enters desired changes and presses submit.
5. Server makes changes to records.
6. <- Client reports the sucess/failure of the changes.

##### Extensions
---
|UC-12            | EditSprint|
|--------------------:|--------------|
|Related Requirements:|REQ-1, REQ-7|
|Initiating Actor:|Manager|
|Actor's Goals|To change the attributes of a sprint such as its name or target completion date.|
|Participating Actors|None|
|Preconditions|Selected sprint exists.  A project exists|
|Postconditions|Selected sprint is assigned the attributes specified by the manager.|
##### Flow of Events
1. -> Manager selects the edit button on a sprint.
2. Server validates the user has the appropriate permissions.
3. <- Client displays the edit sprint options menu.
4. -> Manager enters desired changes and selects submit.
5. Server makes changes to records.
6. <- Client reports the sucess/failure of the changes.
##### Extensions
---
This is not a Use Case this is a property of how the permissions system operates. Better as requirement. Consider removal.

|UC-13            | PermsNewProj|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-14            | InviteToProj|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-5|
|Initiating Actor:|Manager|
|Actor's Goals|To add a new programmer to their project|
|Participating Actors|RegUser|
|Preconditions|The desired used is not part of the project in question.|
|Postconditions|The desired User will be able to join the relevant project.|
##### Flow of Events
1. -> Manager selects the invite user action for their project.
2. Server checks that the user is a manager and has appropriate permisions.
3. <- Client displays the invite user menu.
4. -> Manager enters the username of the relavent user.
5. <- Server sends confirmation message
##### Extensions
---
This is not a Use Case this is a property of how the permissions system operates. Better as requirement. Consider removal.

|UC-15            | DefaultProjPerms|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
We should probably get rig of UC-16 and merge it w/ UC-14

|UC-16            | InviteNotify|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-5|
|Initiating Actor:|Manager|
|Actor's Goals|To add the desired new programmer to their team.|
|Participating Actors|RegUser|
|Preconditions|Manager invite to the new prorammer.|
|Postconditions|RegUser is notified that they have an invitation to become a programmer on the relavent project.|
##### Flow of Events
1. -> Manager finishes sending invite.
2. <- Server sends invite once the target user's client is connected to the network.
3. -> Target user accepts invite.
##### Extensions
---
|UC-17            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-18            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-19            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-20            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-21            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-22            | Start Work on a Task|
|--------------------:|--------------|
|Related Requirements:|REQ-5, REQ-1, |
|Initiating Actor:|Programmer|
|Actor's Goals|Log the time the actor began working on a task|
|Participating Actors|none|
|Preconditions|A Task must exist|
|Postconditions|start time is logged and saved|
##### Flow of Events
1. →User:selects the task that they want to begin 
2. ←System:Presents options for task
3. →User: selects start work
4. System logs start time
##### Extensions
(4b). 
	1.←System: indicates to user that task is already started
---
|UC-23            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-24            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-25            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions
---
|UC-26          | View Statistics|
|--------------------:|--------------|
|Related Requirements:|REQ-11, REQ-2|
|Initiating Actor:|Programmer/Manager|
|Actor's Goals|View Detailed Statistics about Tasks, projects|
|Participating Actors|None|
|Preconditions|Actor must have appropriate permissions to access given tasks and projects|
|Postconditions|Detailed statistics are returned to the User|
##### Flow of Events
1. →User: selects statistics
2. ←System: Displays Statistics for all projects and tasks User has permissions to view (which could be none).

---
|UC-27            | Filter Statistics|
|--------------------:|--------------|
|Related Requirements:|REQ-11,REQ-2|
|Initiating Actor:|Programmer/Manager|
|Actor's Goals|Change which tasks/projects/sprints are included in statisctics|
|Participating Actors|None|
|Preconditions|Actor must have appropriate permissions to view projects; Actors view is displaying the statistics screen, Statistics Screen Displays available filters|
|Postconditions|View will be updated and limited to the given parameters|
##### Flow of Events
1. →User: Selects a filter(filter out project etc, include project etc.)
2. System calculates statistics based on filters
3. ←System: Displays filtered Statistics


