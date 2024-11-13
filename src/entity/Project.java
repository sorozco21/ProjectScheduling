package entity;

import java.time.LocalDate;
import java.util.List;

public class Project {
    private String projectName;
    private List<Task> tasks;
    private LocalDate startDate;

    public Project(){}

    public Project(String projectName, List<Task> tasks){
        this.projectName = projectName;
        this.tasks = tasks;
        this.startDate = LocalDate.now();
    }

    public String getProjectName(){ return this.projectName; }
    public void setProjectName(String projectName){ this.projectName=projectName; }

    public List<Task> getTasks(){ return this.tasks; }
    public void setTasks(List<Task> tasks){ this.tasks=tasks; }

    public void addTask(Task task){
        this.tasks.add(task);
    }
    public void removeTask(Task task){
        this.tasks.remove(task);
    }
    public void removeTask(String taskName){
        this.tasks.removeIf(t -> t.getTaskName().equals(taskName));
    }

    public LocalDate getStartDate(){ return this.startDate; }
    public void setStartDate(LocalDate startDate){ this.startDate=startDate; }

}
