Project/Plan Scheduler 
The Project Scheduler is a Java-based console application designed to help users create, manage, and visualize project timelines with tasks and dependencies. This tool enables project planners to organize tasks within a project, set up task dependencies, and schedule start dates in a streamlined way. It uses Kahn's Topological Sort.

Features
1. Project Creation: Users can create new projects
2. Task Management:
   2.1 Add Tasks
   2.2 Add task dependencies
3. Schedule the tasks in a project/plan


Requirements          
1. We need to calculate calendar schedules for project plans
2. Each project plan consists of tasks. Every task has a certain duration.
3. A task can depend on zero or more other tasks. If a task depends on some other tasks, it can only be started after these tasks are completed
4. So, for a set of tasks (with durations and dependencies), the solution for the challenge should generate a schedule, i.e. assign Start and End Dates for every task
5. It is ok to have a console app
6. The solution should be pushed to GitHub
