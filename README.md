# Yet Another Agile Manager (YAAM)  
  
## Introduction  (@ryan-cr → TODO write)
Here is where you motivate some of your own ideas and describe your customer.  
  
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
RescueTime is desktop time-management app intended to help users analyze their daily program usage habits. Although not necessarily intended for tracking time for projects or its various components, it is especially apt at recording time spent on relevant program usage data. The application records time usage on all programs based on process image names and mouse and keystroke interaction. The desktop application has minimal UI and serves as principally a usage recorder; all statistical aggregate data is viewed on their website's dashboard.

Functionalities related to YAAM:
- Uses a desktop application to record relevant data.
- Records time spent by using software image names and on keystroke/mouse interaction.
- Providing a statistical analysis of time spent on interacted software (with the modification: for a specific task or project).
    - Statistical analysis is interactive exhaustive (permits viewing raw data interactively).
    - Statistical analysis is well formatted - makes apt use of bar/pie charts and graphs.
    - Categorizes usage into functionality groups (e.g. software development, communication & scheduling, utilities, entertainment).
 
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
Yaam is a client-server, application with all data stored on the server. Customers will host a yaam server and make accounts for their team on that server. They will run a client side desktop application in order to access that server. The client will observe the developer and issue smart reminders to log work done when the developer uses IDEs for example. This will allow us to bring  Rescue Times tracking ability, and TimeSheets intentional tracking with Agilean's application domain specific reporting, and project progress tracking.   
  


## Project Architecture (@ryan-cr TODO → Write)
Your overview should lead naturally into your architecture. Here you have to do the best you can if you have limited experience. Decide based on your vision of what your project should be with the understanding that you may have to learn some specific technologies later. You do not have specify specific technology now. If you are going to use an SQL database to store task data, then just say that and use that language on any diagrams.  
  
## Requirements (@ryan-cr, @JohnPCsus, @lihaven TODO → Review)
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


----------

first


\- ryan

