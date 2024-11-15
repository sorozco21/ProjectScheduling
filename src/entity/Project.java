package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Project {
    private String projectName;
    private List<Task> tasks = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(){
        this.startDate = LocalDate.now();
    }
    public Project(String projectName){
        this.projectName=projectName;
        this.startDate = LocalDate.now();
    }
    public Project(String projectName, LocalDate startDate){
        this.projectName=projectName;
        this.startDate = startDate;
    }

    public Project(String projectName, List<Task> tasks){
        this.projectName = projectName;
        this.tasks = tasks;
        this.startDate = LocalDate.now();
    }
    public Project(String projectName, List<Task> tasks, LocalDate startDate){
        this.projectName = projectName;
        this.tasks = tasks;
        this.startDate = startDate;
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
    public LocalDate getEndDate(){ return this.endDate; }
    public void setEndDate(LocalDate endDate){ this.endDate=endDate; }

    @Override
    public String toString() {
        return getProjectWithStartEnd() + " \n" +
                tasks.stream()
                        .filter(Objects::nonNull)
                        .map(Task::toString)
                        .collect(Collectors.joining("\n"));
    }

    public void addSubTask(String mainTaskName, String subTaskName) {
        Optional<Task> mainTaskOptional = tasks.stream()
                .filter(task -> task.getTaskName().equalsIgnoreCase(mainTaskName))
                .findFirst();

        Optional<Task> subTaskOptional = tasks.stream()
                .filter(task -> task.getTaskName().equalsIgnoreCase(subTaskName))
                .findFirst();

        if (mainTaskOptional.isPresent() && subTaskOptional.isPresent()) {
            Task mainTask = mainTaskOptional.get();
            Task subTask = subTaskOptional.get();
            mainTask.addSubTask(subTask);
            System.out.println("Subtask added as a dependency.");
        } else {
            System.out.println("**Either the main task or subtask was not found in the project.");
        }
    }
    public String getProjectWithStartEnd(){
        return projectName + " from " +startDate+" to "+endDate;
    }
}
