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
- Projects → Releases → Sprints → Tasks
- Projects → Impediments
   
##### Reporting  
Agilean offers many views of the project. The Requirements view displays all of the Epics, User Stories and Tasks. The board view displays all of the tasks in the current sprint on a swim lane board with lanes representing various levels of completion, from Backlog to Done. Agilean also offers a comprehensive reporting view, showing how many tasks are assigned to each developer, how many tasks are in each stage of completion, a burn-down chart, among others. 
  
  
### [RescueTime](https://www.rescuetime.com/)   
##### Overview
RescueTime is desktop time-management app intended to help users analyze their daily program usage habits. Although not necessarily intended for tracking time for projects or its various components, it is especially apt at recording time spent on relevant program usage data. The desktop application has minimal UI and serves as principally a usage recorder; all statistical aggregate data is viewed on their website's dashboard.

##### Time Tracking
RescueTime uses their desktop application to record time usage on all programs based on process image names and mouse and keystroke interaction. All program usage data is sent to their remote server under the user's registered account; since data is remotely stored, one user may use RescueTime from various computers and all their data is synchronized.

##### Statistical Aggregation
RescueTime's principal selling point is its complex interactive and exhaustive statistical aggregation breakdown. Its dashboard permits viewing both derived and raw data interactively. Its breakdown features apt usage of bar charts, pie charts, and graph statistics that can be viewed for a specific day, week, month, or year. Additionally, it categorizes various softwares into functionality groups (e.g. software development, communication & scheduling, utilities, entertainment).

 
### [Timesheet](https://www.timesheet.io/)  
#### Overview 
Timesheet is and individual user time tracking solution with a focus on tracking contract work. Although it includes a lot of features that probably won't want to consider for YAAM (including, but not limited to billing and expense tracking because the assumption is that YAAMs users are salaried), it is primarily a time and completed task tracking application. This makes it relevant our research because it's conceptually very similar to out first TM application in class, but with a GUI and some critical features added, primarily projects, tags, and GUIs on web and mobile.

The general first time user flow in Timesheets is for a user to create a project, maybe a tag or two, and then start tracking tasks with the timer, once tasks are stopped they will ask for tags and a description. Note that the mobile app is free, but the web app is not, therefore the mobile app with timer is the most common use case.

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
__Timer:__ A time with start, stop and pause functionality allows a user to track their tasks in real time on their mobile phone (not available on web). If paused, once the task has been resumed the user can log some details about the break. Once a task has been stopped a user can log details about a task, including description of the task, change projects, start/stop times, etc.

__Tasks:__ There are two different task views available. The first is a summary list of tasks including task names, and minimal information on start/stop times, break times, tags, billing information, and description. The second is a calendar view (with week and month views) that allows more visibility into. Tasks also include information about expenses, billing, and happiness with the task performed which are not relevant to our research.

__Tags:__ Tasks can be tagged with custom tags, colored with an RGB color value system, teams are able to use these to indicate whatever they need and are used as a filter for statistics reporting. In addition, the properties of each tag includes a statistical review of what projects the tag was used on.

__Statistics:__ The statistics reporting is a major feature of timesheets as it allows users to see how much overall time they are spending and where that time is going. Some of the more relevant features are: Date selections (several quick access pre-defined periods, as well as custom periods), Time Spent, Break Time, Progress (since it doesn't track goals, this is more like a sum of time spent day to day), Average Daily Working Hours, Time Spent Per Tag (a pie chart and hour break down text list), Projects (a pie chart and hour breakdown in text).

__Contracting:__ There are numerous features for contracting work that are not relevant to our project that aren't detailed here, but include: tracking if a task is billable and if it has been paid, hourly rates, overtime, and invoices.
down text).

## Project Overview  
YAAM is a client-server application with all data stored on a cloud server as a service. Customers will be able to create their own user accounts and run a client-side desktop application in order to access that server. The client will observe the developer and issue smart reminders to log work done when the developer uses IDEs, for example. This will allow us to bring  Rescue Times tracking ability, and TimeSheets intentional tracking with Agilean's application domain specific reporting, and project progress tracking.   
  
With automatic reminders tracking data will be more reliable. This will enable project leads to understand their teams velocity and generate more accurate predictions about future productivity. With online storage, YAAM will provide project managers with powerful options for communicating with their customers, and permissions will allow those customers to only see what information matters to them, without all of the technical details. 

## Project Architecture  
The project architecture for our software incorporates various ideas from both our team and our product reviews. Our architecture will make use a cloud-based client-server approach to maximize team collaboration and accessibility. All software implementations (client and server) will be written in Java 8, with the exception of the server's persistent data storage. The client's role is to provide the means for inputting and reviewing project data. The server's role is to serve as a database endpoint to synchronize all relevant project data to an entire development team.

#### Client
The only software client will be a JavaFX-driven GUI desktop application. Users will be able to create, edit, and interact with projects and tasks; behind the scenes, all modification data will be sent to the server to handle and store. The desktop application will serve to organize tasks, track time spent on tasks, and provide a statistical aggregation breakdown for tasks. The client will synchronize all modified project data with the cloud server.

The following third-party softwares will be used in the client architecture:
- To provide a GUI with rich UI design:
  - **[JavaFX](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html)**: "JavaFX is the next step in the evolution of Java as a rich client platform. It is designed to provide a lightweight, hardware-accelerated Java UI platform for enterprise business applications."
  - **[JFoenix](https://github.com/jfoenixadmin/JFoenix)**: "JFoenix is an open source Java library, that implements Google Material Design using Java components"
- To track time spent working on specific programs for tasks:
  - **[Java Native Access](https://github.com/java-native-access/jna) (JNA)**: "JNA provides Java programs easy access to native shared libraries without writing anything but Java code - no JNI or native code is required." (e.g. access platform specific libraries to track program interaction)
  - **[system-hook](https://github.com/kristian/system-hook)**: "Java (low-level) System Hook provides a very light-weight global keyboard and mouse listener for Java."

#### Server
The cloud server software will serve to store and retrieve all data related to users, projects, and tasks. Clients will send appropriate user data to the cloud server via a web server with a HTTP API endpoint that will process and store appropriate information. The web server will make use of MySQL to store all relevant data related to users, projects, and tasks.

The following third-party softwares will be used in the server architecture:
- To provide a HTTP API endpoint:
  - **[Embedded Jetty](https://www.eclipse.org/jetty/)**: Eclipse Jetty provides a Web server and javax.servlet container, plus support for HTTP/2, WebSocket, OSGi, JMX, JNDI, JAAS and many other integrations."
    - **[Spark](http://sparkjava.com/)**: "A micro framework for creating web applications in Kotlin and Java 8 with minimal effort." Spark (which runs on Jetty) will be used to mitigate the overhead of programmatically creating a Jetty server for the HTTP API.
  - **[JSON](https://github.com/stleary/JSON-java)**: "JSON is a light-weight, language independent, data interchange format." JSON will be used for data payloads for receiving and sending data to clients.
- To persistently store data:
  - **[MySQL](https://www.mysql.com/)**: MySQL is a open source, relational SQL database management system. 
  - **[HikaryCP](https://github.com/brettwooldridge/HikariCP)**: "HikariCP is a 'zero-overhead' production ready JDBC connection pool"
  
  
## Project Requirements
  
|REQ-###|Priority|Description|
|------|---|-----------|
|**REQ-1**| 20 |The cloud server application shall store and provide a record of projects, sprints, tasks, and their associated attributes.|
|**REQ-2**| 14 |The desktop client application shall be the GUI access point for retrieving and modifying data (e.g. retrieve and modify the record of sprints, tasks, and their associated attributes) on the cloud server.|
|**REQ-3**| 13 |Users shall be able to register an account on the cloud server.|
|**REQ-4**| 15 |Users shall be able to create a new project.|
|**REQ-5**| 10 |Users, as a the project creator, shall be able to invite additional registered users to view (and potentially modify) a project.|
|**REQ-6**| 9 |Users who are project creators shall be able to set read, write, or other varying permissions for invited/added users.|
|**REQ-7**| 17 |Users with appropriate permissions shall be able to create a sprint or task. |
|**REQ-8**| 7 |Users with appropriate permissions shall be able to specific attributes for sprints and tasks, such as descriptions, assignee(s), task size, due dates, and varying other properties.|
|**REQ-9**| 6 |Users with the appropriate permissions shall be able to specify additional attribute types on per-project basis. For example: creating customizable task sizes.|
|**REQ-10**| 19 |Users with the appropriate permissions shall be able to log the times when they started and stopped working on a task; they shall also be able to edit their own time task time histories to rectify clerical errors.|
|**REQ-11**| 16  |Users shall be able to view work summaries for a specific project, sprint, task, or user.|

__Priority justifications:__ Most important is anything that was part of the original TM program and adding agile functionality, the second most important thing is the cloud server implementation because it's easy and helps teams work together better, the third most important thing is that the program be able to support multiple projects and then multiple teams, finally it's important to manager user permissions so teams don't have to worry about correcting mistakes or misunderstandings with changes made by inexperienced or uninformed users. 

##### Possible Future Requirements
|REQ-###|Description|
|------|-----------|
|**REQ-XX1**|Users with the appropriate permissions will be able to define and attach custom tags to tasks, on a per-project basis.|
|**REQ-XX2**|Users shall be able to track time spent on tasks by checking user software interactivity|
|**REQ-XX3**|The client application shall run in the background on the developers computer and shall use heuristics to determine if the developer is working on a task assigned to them.  If it makes such a determination and the developer **has not** logged work as being in progress the application shall send the a notification that will remind them to log work as being in progress.|

## Use Cases  (@paul-mchugh TODO → Get use cases tasks list, assign out)
Your requirements and previous sections should lead to your use cases. How will users use the system? What are the types of users?  This sections should include detailed use cases as well as an accountability matrix that ties your requirements to your use cases.

__Actors__
Anyone - Genearlly meaning anyone or thing acting as a human person
RegUser - Genearlly meaning anyone who has registered and has a valid user account
Developer - Generally meaning the user with the lowest permissions for a project
Manager - Generally meanting the user with a high level of permissions for a project
Server - The data warehouse manager
Client - The customer service associate

__Derived Use Cases From Requirements__

|Actor     |Actor's Goal                                                                            |Use Case Name         |
|----------|----------------------------------------------------------------------------------------|----------------------|
|RegUser   |Will be able to create projects and have them saved on the server.                      |CreateProj(UC1)       |
|Manager   |Will be able to create sprints and have them saved on the server.                       |CreateSprints(UC2)    |
|Developer |Will be able to create tasks and have them saved on the server.                         |CreateTasks(UC3)      |
|Developer |Will be able to create and modify task attributes and have them saved on the server.    |ModAttribs(UC4)       |
|Developer |Will be able to move a task from one sprint (or backlog) to another.                    |MoveTasks(UC5)        |
|Manager   |Managers will be able to edit a sprints attributes such as its due date and name.       |EditSprints(UC6)      |
|Anyone    |Is able register a new account through the desktop client with minimal Personal information.|RegAccout(UC10)   |
|RegUser   |Will be able to log into their account on the client application.                       |OpenDsktpClient(UC11) |
|RegUser   |Users will be able to view the state of projects they are a part of.                    |ViewProject(UC12)     |
|Manager   |Should be able to invite additional register users to access a project                  |InviteToProj(UC14)    |
|RegUser   |Should be able to see notifications about invitations to projects.                      |InviteNotify(UC16)    |
|Manager   |Should be able to manager permissions and access of other users on a project.           |MngProjUsers(UC17)    |
|Manager   |Will be able to customize task sizes as project parameters.		     		            |DefineSizes(UC18)     |
|Manager   |Will be able to set a task size from one of the task size project parameters.	        |SetTaskSize(UC19)     |
|Developer|Will be able to set a task size from one of the task size project parameters.	        |SetTaskSize(UC19)     |
|Manager   |Will be able to customize tags as project parameters.			                	    |DefineTags(UC20)      |
|Manager   |Will be able to set a task tags from one of the tag project parameters.		            |SetTaskTags(UC21)     |
|Developer|Will be able to set a task tags from one of the tag project parameters.		            |SetTaskTags(UC21)     |
|Manager   |Start work on a task.								                                    |StartTask(UC22)       |
|Manager   |Stop work on a task.								                                    |StopTask(UC23)        |
|Manager   |Edit start and stop times on a task.						                            |EditTaskTimes(UC24)   |
|Manager   |Add start and stop time on a task in the even they forgot to log the task entirely.	    |AddTaskTimes(UC25)    |
|Developer|Start work on a task.								                                    |StartTask(UC22)       |
|Developer|Stop work on a task.								                                        |StopTask(UC23)        |
|Developer|Edit start and stop times on a task.					 	                                |EditTaskTimes(UC24)   |
|Developer|Add start and stop time on a task in the even they forgot to log the task entirely.	    |AddTaskTimes(UC25)    |
|Manager   |View statistical summaries page.							                            |ViewStats(UC26)       |
|Manager   |Manage view using filters with various project, sprint, and task attributes.	        |FilterStats(UC27)     |
|Developer|View statistical summaries page.						                             	    |ViewStats(UC26)       |
|Developer|Manage view using filters with various project, sprint, and task attributes.	            |FilterStats(UC27)     |

__Accountability Matrix__ (Assuming this means Tracability Matrix?)
There are more use cases than requirements, so I turned the table from what is in the  

|Req't|REQ1 |REQ2 |REQ3 |REQ4 |REQ5 |REQ6 |REQ7 |REQ8 |REQ9 |REQ10|REQ11|Max PW|Total PW|
|----:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|------|--------|
|PW   | 20  | 14  | 13  | 15  | 10  |  9  | 17  |  7  |  6  | 19  | 16  | ---- | ------ |
|UC1  | x   | x   |     |  x  |     |     |     |     |     |     |     |      |        | 
|UC2  | x   |     |     |     |     |     | x   |     |     |     |     |      |        |
|UC3  | x   |     |     |     |     |     | x   |     |     |     |     |      |        |
|UC4  | x   |  x  |     |     |     |     |     |  x  |     |     |     |      |        |
|UC5  | x   |  x  |     |     |     |     |     |  x  |     |     |     |      |        |
|UC6  | x   |  x  |     |     |     |     |     |  x  |     |     |     |      |        |
|UC7  |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC8  |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC9  |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC10 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC11 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC12 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC13 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC14 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC16 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC17 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC18 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC19 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC20 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC21 | x   |     |     |     |     |     |     |  x  |     |     |     |      |        |
|Uc22 | x   |     |     |     |  x  |     |     |     |     |     |     |      |        |
|UC23 | x   |     |     |     |  x  |     |     |     |     |     |     |      |        |
|UC24 | x   |     |     |     |     |     |     |     |     |  x  |     |      |        |
|UC25 |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC26 |     |  x  |     |     |     |     |     |     |     |     |  x  |      |        |
|UC27 |     |  x  |     |     |     |     |     |     |     |     |  x  |      |        |
---
__Detailed Use Cases__

|UC-1            | Create Project|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-2, REQ-4|
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
|UC-2            | Create Sprint|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-7|
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

|UC-3            | Create Task|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-7,|
|Initiating Actor:|Anyone|
|Actor's Goals|Create task within a project|
|Participating Actors|None|
|Preconditions|Actor have sufficient permissions within the project, project must exist already|
|Postconditions|Task is created on server and assosciated with project|
##### Flow of Events
 1. → User: selects create task function
 2. ← System: Displays a form to user
 3. User: Fills out Form
 4. → User: Submits Form
 5. ← System: 
	 * (a) system stores task information
	 * (b) signals completions
---
##### Extensions   

5(b).
1. ← System: 
	* (a) signals that account information is incomplete/not unique
	* (b) returns form to user  
2.  return to step 3.

---


---
|UC-4            | Modify task attributes|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-2,REQ-8|
|Initiating Actor:|Developer|
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
|UC-5            | Move Task to sprint/backlog|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-2,REQ-8|
|Initiating Actor:|Developer|
|Actor's Goals|To move a task either off of the backlog and into a sprint, or vice-versa|
|Participating Actors|None|
|Preconditions|Tasks must already exist, a sprint must already exist, user must have adequate permissions|
|Postconditions|a sprint will have a given task assosciated with it|
##### Flow of Events
1. ->User: Selects sprints
2. <-System:displays sprints, and backlog
3. ->User: selects sprint and destination (backlog/sprint).
4. <-System:
	* (a) Moves task into indicated sprint
	* (b) Indicates to user that sprint has been moved.

---
|UC-6            | Edit Sprint Attributes|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-2, REQ-8|
|Initiating Actor:|Manager|
|Actor's Goals|change the attributes (due date,name, etc.) of a sprint|
|Participating Actors|None|
|Preconditions|A sprint must already exist, user must have sufficient permissions|
|Postconditions|System will record changes to sprints attributes|
##### Flow of Events
1. ->User: Selects sprint to change
2. <-System:displays sprint and associated data
3. ->User:
	* (a) selects information to alter
	* (b) enters new value
4. <-System: 
	* (a) records new value(s).
	* (b) indicates to user that change is complete.

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
|UC-10            | Register Account|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-2,REQ-3|
|Initiating Actor:|Anyone|
|Actor's Goals|Create an account on the server|
|Participating Actors|None|
|Preconditions|Actor must not already have an account on the server|
|Postconditions|User Account information stored by the server, User is given Manager permissions in new Project|
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
|UC-11            | EditProject|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-9|
|Initiating Actor:|Manager|
|Actor's Goals|To change properties global to a project such as the tags which are available to tasks.|
|Participating Actors|None|
|Preconditions|The selected project exists.|
|Postconditions|The selected project has the properties assigned by the manager.|
##### Flow of Events
1. → Manager selects edit project properties.
2. Server validates the user is a manager.
3. ← Client displays edit project menu.
4. → Manager enters desired changes and presses submit.
5. Server makes changes to records.
6. ← Client reports the sucess/failure of the changes.

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
1. → Manager selects the edit button on a sprint.
2. Server validates the user has the appropriate permissions.
3. ← Client displays the edit sprint options menu.
4. → Manager enters desired changes and selects submit.
5. Server makes changes to records.
6. ← Client reports the sucess/failure of the changes.
##### Extensions

---
|UC-14            | InviteToProj|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-5|
|Initiating Actor:|Manager|
|Actor's Goals|To add a new Developer to their project|
|Participating Actors|RegUser|
|Preconditions|The desired used is not part of the project in question.|
|Postconditions|The desired User will be able to join the relevant project.|
##### Flow of Events
1. → Manager selects the invite user action for their project.
2. Server checks that the user is a manager and has appropriate permisions.
3. ← Client displays the invite user menu.
4. → Manager enters the username of the relavent user.
5. ← Server sends confirmation message
##### Extensions

---

|UC-16            | InviteNotify|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-5|
|Initiating Actor:|Manager|
|Actor's Goals|To add the desired new Developer to their team.|
|Participating Actors|RegUser|
|Preconditions|Manager invite to the new prorammer.|
|Postconditions|RegUser is notified that they have an invitation to become a Developer on the relavent project. New Developers Permissions set to Default|
##### Flow of Events
1. → Manager finishes sending invite.
2. ← Server sends invite once the target user's client is connected to the network.
3. → Target user accepts invite.
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
|UC-21            | Set Task Tags|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-8|
|Initiating Actor:|Manager,Developer|
|Actor's Goals|Associate a given tag with a given task|
|Participating Actors|None|
|Preconditions|Tag must already exist, task must already exist|
|Postconditions|System records that tag is associated with task|
##### Flow of Events
1. ->User: selects task to tag
2. <-System: Presents Options for Task
3. ->User:Selects add tag
4. <-System: Presents available tags
5. ->User: Selects desired tag
6. <-System:
	* (a) assosciates tag with task
	* (b) indicates to user that task is now tagged
##### Extensions

---
|UC-22            | Start Work on a Task|
|--------------------:|--------------|
|Related Requirements:|REQ-5, REQ-1, |
|Initiating Actor:|Developer|
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
|UC-23            | Stop Work on a Task|
|--------------------:|--------------|
|Related Requirements:|REQ-1, REQ-5|
|Initiating Actor:|Developer|
|Actor's Goals|Log the time user stopped working on a task|
|Participating Actors|None|
|Preconditions|Task must have already been started by user(UC-22)|
|Postconditions|Stop time logged by system|
##### Flow of Events
1. →User:selects the task that they want to stop 
2. ←System:Presents options for task
3. →User: selects stop work
4. System logs stop time
##### Extensions
(4b). 
	1.←System: indicates to user that task is already stopped
	
---
|UC-24            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-10|
|Initiating Actor:|Developer|
|Actor's Goals|Edit start and stop time entries for a task to correct erroneous data|
|Participating Actors|None|
|Preconditions|task must already exist with times logged |
|Postconditions|Selected Time entries will be updated|
1. →User: indicates task to edit
2. ←System: displays detailed information about task
3. →User: User indicates times to change
4. ←System: 
	* (a) updates times 
	* (b) indicates to user that times are logged.
##### Extensions
(4b).
	1. <-System: indicates to user that times cannot be saved due to overlap.See UC-22,UC-23.
	
---

|UC-25            | Add task times|
|--------------------:|--------------|
|Related Requirements:|REQ-1,REQ-10|
|Initiating Actor:|Developer|
|Actor's Goals|Enter data for work that was not recorded due to a user error.|
|Participating Actors|None|
|Preconditions|task must exist|
|Postconditions|a new start and stop time will be added to the task|
##### Flow of Events
1. →User: indicates task to edit
2. ←System: displays detailed information about task
3. →User: User indicates times to add 
4. ←System: 
	* (a) adds times to task see (UC-22,UC-23)
	* (b) indicates to user that times are logged.

---
|UC-26          | View Statistics|
|--------------------:|--------------|
|Related Requirements:|REQ-11, REQ-2|
|Initiating Actor:|Developer/Manager|
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
|Initiating Actor:|Developer/Manager|
|Actor's Goals|Change which tasks/projects/sprints are included in statisctics|
|Participating Actors|None|
|Preconditions|Actor must have appropriate permissions to view projects; Actors view is displaying the statistics screen, Statistics Screen Displays available filters|
|Postconditions|View will be updated and limited to the given parameters|
##### Flow of Events
1. →User: Selects a filter(filter out project etc, include project etc.)
2. System calculates statistics based on filters
3. ←System: Displays filtered Statistics


