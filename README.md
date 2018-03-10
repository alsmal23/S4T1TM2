# Yet Another Agile Manager (YAAM)  
  
## Introduction  
Here is where you motivate some of your own ideas and describe your customer.  
  
  
## Product Reviews  
### [Agilean](https://agilean.in/) 
Agilean is an online SaaS agile development team management system. Agilean comes in two flavors. The Kanban flavor and the scrum flavor. This changes how task progress is managed and viewed. Kanban supports a more continuous work flow, while scrum is more of a sprint style work flow.  We chose to review the Scrum flavor as that seemed to more closely correspond to sprints in Agile Development.  
#####Task Management
Agilean allows many users to view the progress of the many projects. Users have roles for each project, Users are developers on some projects, but may be managers/team leads on other projects.  
On the requirements tab managers create Epics. Epics are assigned to projects. User Stories are assigned to epics. Tasks are assigned to user stories. And sub-tasks can be associated with tasks. Tasks are assigned story points.Tasks can be tagged with custom tags, and colored from a set of predefined colors, teams are able to use these in indicate whatever they need. A task may be tagged however a team finds useful.   

Managers then create Sprints and Releases, and assign tasks to them.  
- Projects → Epics → User Stories → Tasks (→ Sub-tasks)  
- Releases → Sprints → Tasks   
#####Reporting
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

  
### [Timesheets](https://www.timesheets.com/)  
  
  
## Project Overview  
Here you bring together your introduction and lessons learned from the reviews. What are you doing the same, what is different?  
  
  
## Project Architecture  
Your overview should lead naturally into your architecture. Here you have to do the best you can if you have limited experience. Decide based on your vision of what your project should be with the understanding that you may have to learn some specific technologies later. You do not have specify specific technology now. If you are going to use an SQL database to store task data, then just say that and use that language on any diagrams.  
  
  
## Requirements
|Req#|Description|
|----|-----------|
|**REQ-1**|The server application must maintain and provide a record of sprints, tasks, and their associated attributes.|
|**REQ-2**|The client application must be able to retrieve the record of sprints, tasks, and their associated attributes from the server.|
|**REQ-3**|Users shall be able to view the project record including the sprints and their deadlines, the tasks and their associated attributes such as tags, assigned developer, description, and task size, and any other properties of the project.|
|**REQ-4**|Users that are assigned the attribute manager shall be able to creat new tasks, create new sprints, assign attributes to both tasks and sprints, create new accounts for developers on the server, and reset a developer's password.|
|**REQ-5**|Developers shall be able to log the times when they started and stopped work on tasks, including tasks that they are not assigned.|
|**REQ-6**|Developers shall be able to amend their task histories to rectify user or technical errors.  All task history amendments shall be logged.|
|**REQ-7**|Developers shall be able to view a work summary of their own work that shall include information about how much time they have spent on each task.|
|**REQ-8**|Managers shall be able to view the work summaries of all developers including the amendments made to the developer's task history.|

## Use Cases  
Your requirements and previous sections should lead to your use cases. How will users use the system? What are the types of users?  This sections should include detailed use cases as well as an accountability matrix that ties your requirements to your use cases.


----------

first

\- ryan