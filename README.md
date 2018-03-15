# Yet Another Agile Manager (YAAM)  
  
## Introduction
  The agile development process naturally tends to be both sporadic and volatile during the iterative design and implementation process. Project organization and group communication play critical roles in attempting to streamline the development process. The effective goal of the YAAM project is to create a task management software that can bridge the gap between development and organization for small development teams. The software is intended to allow managers and developers alike to organize project tasks and seamlessly track the time spent on completing them. Furthermore, the software will aggregate project task data into a complex statistical summary report for supervisors.
  
## Product Reviews 
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

#### Overview 
Timesheet is and individual user time tracking solution with a focus on tracking contract work. Although it includes a lot of features that probabably won't want to consider for YAAM (including, but not limited to billing and expense tracking because the assumption is that YAAMs users are salaried), it is primarly a time and completed task tracking applciation. This makes it relelevant our research because it's conceptually very similar to out first TM application in class, but with a GUI and some critical features added, primarily projects, tags, and GUIs on web and mobile. 

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
Timer
A time with start, stop and pause functionatliy allows a user to track their tasks in real time on their mobile phone (not available on web). If paused, once the task has been resumed the user can log some details about the break. Once a task has been stopped a user can log details about a task, including description of the task, change projects, start/stop times, etc.

Tasks
There are two different task views avaiable. The first is a summary list of tasks including task names, and minimal information on start/stop times, break times, tags, billing information, and description. The second is a calendar view (with week and month views) that allows more visability into. Tasks also include information about expenses, billing, and happiness with the task performed which are not relevant to our research.  

Tags
Tasks can be tagged with custom tags, colored with an RBG color value system, teams are able to use these to indicate whatever they need and are used as a filter for statistics reporting. In addition, the properties of each tag includes a statitical review of what projects the tag was used on.

Statistics
The statistics reporting is a major feature of timesheets as it allows users to see how much overall time they are spending and where that time is going. Some of the more relevant features are: Date selections (several quick access pre-defined periods, as well as custom periods), Time Spent, Break Time,  Progress (since it doesn't track goals, this is more like a sum of time spent day to day), Average Daily Working Hours, Time Spent Per Tag (a pie chart and hour break down text list), Projects (a pie chart and hour breakdown in text). 

Contracting
There are numerous features for contracting work that are not relevant to our project that aren't detailed here, but inlucde: tracking if a task is billable and if it has been paid, hourly rates, overtime, and invoices.


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
|**REQ-11**|Users shall be able to view work summaries for a specific project, sprint, task, or user.|

##### Possible Future Requirements
|REQ-###|Description|
|------|-----------|
|**REQ-XX1**|Users with the appropriate permissions will be able to define and attach custom tags to tasks, on a per-project basis.|
|**REQ-XX2**|Users shall be able to track time spent on tasks by the desktop client recording time spent based on user software interactivity|
|**REQ-XX3**|The client application shall run in the background on the developers computer and shall use heuristics to determine if the developer is working on a task assigned to them.  If it makes such a determination and the developer **has not** logged work as being in progress the application shall send the a notification that will remind them to log work as being in progress.|

## Use Cases 
Your requirements and previous sections should lead to your use cases. How will users use the system? What are the types of users?  This sections should include detailed use cases as well as an accountability matrix that ties your requirements to your use cases.

__Actors__
- Anyone - Anyone or thing acting as a human person
- RegUser - Anyone who has registered and has a valid user account
- Programmer - A user with the lowest/most-basic permissions for a project
- Manager - A user with the highest/most- permissions for a project

__Derived Use Cases From Requirements__

|Actor     |Actor's Goal                                                                            |Use Case Name         |
|----------|----------------------------------------------------------------------------------------|----------------------|
|Anyone    |Create account                                                                          |CreateAccount(UC1)    |
|RegUser   |Create project (any RegUser can make a project and be a manager within that space)      |CreateProject(UC2)    |
|Manager   |Create sprint                                                                           |CreateSprint(UC3)     |
|Programmer|Create task                                                                             |CreateTask(UC4)     |
|Manager   |Create task                                                                             |CreateTask(UC4)     |
|Programmer|View project data from server (users, projects, sprints, tasks, etc.)                   |ViewProjData(UC5)     |
|Manager   |Request project data (users, projects, sprints, tasks, etc.)                            |ViewProjData(UC5)     |
|Manager   |Add new users to a project and assign permissions to users on a project                 |ManagePermissions(UC9)|
|Programmer|Update and add task information                                                         |EditAddTask (UC10)    |
|Manager   |Update and add task information                                                         |EditAddTask (UC10)    |
|Manager   |Update and add project information                                                      |EditAddProj (UC11)    |
|Manager   |Update and add sprint information                                                       |EditAddSprint (UC12)  |
|And...    |Sooooo many more to come                                                                |..                    |

__Accountability Matrix__ (Assuming this means Tracability Matrix?)
There are more use cases than requirements, so I turned the table from what is in the  

|Req't|REQ1 |REQ2 |REQ3 |REQ4 |REQ5 |REQ6 |REQ7 |REQ8 |REQ9|REQ10|REQ11|REQ12|Max PW|Total PW|
|----:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|------|--------|
|PW   |     |     |     |     |     |     |     |     |     |     |     |     |      |        |
|UC1  |  x  |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC2  | x   |     |     |   x |     |    |    |    |    |     |     |     |      |        |
|UC3  |     |     |  x  |  x  |     |    |    |    |    |     |     |     |      |        |
|UC4  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC5  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC6  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC7  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC8  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC9  |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC10 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC11 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC12 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
|UC13 |     |     |     |     |     |    |    |    |    |     |     |     |      |        |
---
__Detailed Use Cases__

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
|Related Requirements:|REQ-1,REQ-4|
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
|UC-4            | Verb Phrase|
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
|UC-11            | Verb Phrase|
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
|UC-12            | Verb Phrase|
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
|UC-13            | Verb Phrase|
|--------------------:|--------------|
|Related Requirements:||
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
##### Flow of Events
##### Extensions


.
