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
  
  
## Requirements (@ryan-cr, @lihaven TODO → Review)
|Req###|Description|
|------|-----------|
|**REQ-1**|The server application must maintain and provide a record of projects, sprints, tasks, and their associated attributes.|
|**REQ-2**|The client application must be able to retrieve the record of sprints, tasks, and their associated attributes from the server.|
|**REQ-3**|Users shall be able to view the project record including the sprints and their deadlines, the tasks and their associated attributes such as tags, assigned developer, description, and task size, and any other properties of the project.|
|**REQ-4**|Users that are assigned the attribute manager shall be able to create new projects, create new tasks, create new sprints, assign attributes to both tasks and sprints, create new accounts for developers on the server, assign a developer to a project, and reset a developer's password.|
|**REQ-5**|Developers shall be able to log the times when they started and stopped work on tasks, including tasks that they are not assigned.|
|**REQ-6**|Developers shall be able to amend their task histories to rectify user or technical errors.  All task history amendments shall be logged.|
|**REQ-7**|Developers shall be able to view a work summary of their own work that shall include information about how much time they have spent on each task.|
|**REQ-8**|Managers shall be able to view the work summaries of all developers including the amendments made to the developer's task history.|
|**REQ-9**|Users shall only be able to access projects that they are a part of.|
|**REQ-10**|Managers shall be able to choose from either a T-shirt size task system, a points based task size system, or some other user defined task size system and apply these stask sizing systems on a per project basis.|
|**REQ-11**|Managers shall be able to create a custom set of tags which may be assigned to tasks and make them available on a per project basis.|
|**REQ-12**|The client application shall run in the background on the developers computer and shall use heuristics to determine if the developer is working on a task assigned to them.  If it makes such a determination and the developer **has not** logged work as being in progress the application shall send the a notification that will remind them to log work as being in progress.|


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
|Anyone    |Create account                                                                          |CreateAccount(UC1)    |
|RegUser   |Create project (any RegUser can make a project and be a manager within that space)      |CreateProject(UC2)    |
|Manager   |Create sprint                                                                           |CreateSprint(UC3)     |
|Programmer|Create task                                                                             |CreateSprint(UC4)     |
|Manager   |Create task                                                                             |CreateSprint(UC4)     |
|Programmer|View project data from server (users, projects, sprints, tasks, etc.)                   |ViewProjData(UC5)     |
|Manager   |Request project data (users, projects, sprints, tasks, etc.)                            |ViewProjData(UC5)     |
|Server    |Send requested project data to client                                                   |SendData (UC6)        |
|Client    |Display project, sprint, and task data                                                  |DisplayProjData (UC7) |
|Client    |Display statistical data                                                                |DisplayStatsData (UC8)|
|Manager   |Add new users to a project and assign permissions to users on a project                 |ManagePermissions(UC9)|
|Programmer|Update and add task information                                                         |EditAddTask (UC10)    |
|Manager   |Update and add task information                                                         |EditAddTask (UC10)    |
|Manager   |Update and add project information                                                      |EditAddProj (UC11)    |
|Manager   |Update and add sprint information                                                       |EditAddSprint (UC12)  |
|Server    |Store application data (users, projects, sprints, tasks, etc.).                         |StoreData (UC13)      |
|And...    |Sooooo many more to come                                                                |..                    |

__Accountability Matrix__ (Assuming this means Tracability Matrix?)
There are more use cases than requirements, so I turned the table from what is in the  

|Req't|REQ1|REQ2|REQ3|REQ4|REQ5|REQ6|REQ7|REQ8|REQ9|REQ10|REQ11|REQ12|Max PW|Total PW|
|-----|----|----|----|----|----|----|----|----|----|-----|-----|-----|------|--------|
|PW   |    |    |    |    |    |    |    |    |    |     |     |     |      |        |
|UC1  |    |    |    |    |    |    |    |    |    |     |     |     |      |        |
|UC2  |    |    |    |    |    |    |    |    |    |     |     |     |      |        |
|UC3  |    |    |    |    |    |    |    |    |    |     |     |     |      |        |
|And  |so  |on..|    |    |    |    |    |    |    |     |     |     |      |        |
---
__Detailed Use Cases__
(@lihaven TODO → make a detailed use case template)
|UC-1            | Create Account|
|---------------------|--------------|
|Related Requirements:|REQ-1|
|Initiating Actor:|Anyone|
|Actor's Goals|Create an account on the server|
|Participating Actors||
|Preconditions|Actor must not already have an account on the server|
|Postconditions|User Account information stored by the server|
##### Flow of Events
 1. → User: selects create account function
 2. ← System: Displays a form to user
 3. User: Fills out Form
 4. → User: Submits Form
 5. ← System: (a) Stores account information; (b) signals completions
##### Extensions
5.(b) ← System: signals that account information is incomplete/not unique, returns form to user
6. return to step 3.  
---
|UC-2            | Create Project|
|---------------------|--------------|
|Related Requirements:|REQ-1, REQ-4|
|Initiating Actor:||
|Actor's Goals||
|Participating Actors||
|Preconditions||
|Postconditions||
	
