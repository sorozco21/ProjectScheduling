package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Project {
    private String projectName;
    private List<Task> tasks = new ArrayList<>();
    private LocalDate startDate;

    public Project(){
        this.startDate = LocalDate.now();
    }
    public Project(String projectName){
        this.projectName=projectName;
        this.startDate = LocalDate.now();
    }

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

    @Override
    public String toString() {
        return "Project Name: " + projectName + "\n" +
                tasks.stream()
                        .filter(Objects::nonNull)
                        .map(Task::toString)
                        .collect(Collectors.joining("\n"));
    }
}
