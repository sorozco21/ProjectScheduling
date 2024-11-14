import entity.Project;
import entity.Task;

import java.time.LocalDate;
import java.util.*;

public class Scheduler {
    private Project project;
    private Map<Task, Integer> inDegreeMap; // the count of how many task depends on it

    public Scheduler(Project project){
        this.project = project;
        this.inDegreeMap = new HashMap<>();
    }
    public void schedule(){
        List<Task> sortedTasks = topologicalSort();
        List<Task> sortedAndScheduledTask = new ArrayList<>();
        LocalDate currDate = project.getStartDate();
        for(Task task : sortedTasks){
            if(task.getSubTasks().isEmpty()){
                task.setStartAndEndDate(currDate);
            }else{
                LocalDate latestEndDate = project.getStartDate();
                for(Task subTask : task.getSubTasks()){
                    if(subTask.getEndDate() != null && subTask.getEndDate().isAfter(latestEndDate)){
                        latestEndDate = subTask.getEndDate();
                    }
                }
                task.setStartAndEndDate(
                        currDate.isAfter(latestEndDate) ? currDate
                                : latestEndDate.plusDays(1)
                );
            }
            currDate = task.getEndDate().plusDays(1);
            sortedAndScheduledTask.add(task);
        }
        project.setTasks(sortedAndScheduledTask);
        project.setEndDate(currDate);
    }
    
    // use Kahn’s algorithm for Topological Sorting
    private List<Task> topologicalSort(){
        List<Task> sortedTasks = new ArrayList<>();
        Queue<Task> taskQueue = new LinkedList<>();

        //calculate the indegree
        for(Task task : project.getTasks()){
            inDegreeMap.put(task, task.getSubTasks().size());
            if(inDegreeMap.get(task) == 0){
                taskQueue.add(task);
            }
        }

        while(!taskQueue.isEmpty()){
            Task currentTask = taskQueue.poll();
            sortedTasks.add(currentTask);

            for (Task task : project.getTasks()){
                if(task.getSubTasks().contains(currentTask)){
                    int updatedInDegree = inDegreeMap.get(task) -1;
                    inDegreeMap.put(task, updatedInDegree);

                    if(updatedInDegree == 0){
                        taskQueue.add(task);
                    }
                }
            }
        }

        //check for cyclic dependencies
        if(sortedTasks.size() != project.getTasks().size()){
            throw new RuntimeException(("Cycle detected in tasks."));
        }

        return sortedTasks;
    }

    public Project getProject(){return this.project; }
    public void setProject(Project project){ this.project= project;}
}
