package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Task {
    private String taskName;
    private int duration;
    private boolean completed;
    private List<Task> subTasks = new ArrayList<>();

    public Task(String taskName, int duration){
        this.taskName = taskName;
        this.duration = duration;
    }

    // TODO: to be assigned
    private LocalDate startDate;
    private LocalDate endDate;

    public String getTaskName(){ return this.taskName; }
    public void setTaskName(String taskName){ this.taskName=taskName; }

    public int getDuration(){ return this.duration; }
    public void setDuration(int duration){ this.duration=duration; }

    // TODO: create check
    public boolean isCompleted(){ return this.completed; }
    public void setCompleted(boolean isCompleted){ this.completed=isCompleted; }

    // recursive function to check if task and its subtasks are completed
    public boolean areSubTasksCompleted(){
        if(subTasks == null || subTasks.isEmpty()) return true;
        for(Task subtask : subTasks){
            if(!subtask.isCompleted() || !subtask.areSubTasksCompleted()){
                return false;
            }
        }
        return true;
    }


    public List<Task> getSubTasks(){ return this.subTasks; }
    public void setSubTasks(List<Task> subTasks){ this.subTasks=subTasks; }

    public void addSubTask(Task task){
        this.subTasks.add(task);
    }
    public void removeSubTask(Task task){
        this.subTasks.remove(task);
    }
    public void removeSubTask(String taskName){
        this.subTasks.removeIf(t -> t.taskName.equals(taskName));
    }

    public LocalDate getStartDate(){ return this.startDate; }
    public void setStartDate(LocalDate startDate){ this.startDate=startDate; }

    public LocalDate getEndDate(){ return this.endDate; }
    public void setEndDate(LocalDate endDate){ this.endDate=endDate; }

    public void setStartAndEndDate(LocalDate startDate){
        this.startDate = startDate;
        this.endDate = startDate.plusDays(duration);
    }
    @Override
    public String toString(){
        return taskName + " : Start ( " + startDate + " ) - End ( " + endDate + " )";
    }
}
