import entity.Project;
import entity.Task;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Scheduler scheduler = new Scheduler(getSampleProject1());
        scheduler.schedule();
        System.out.println("-----------------------------------");
        System.out.println("Scheduling for : " + scheduler.getProject().getProjectName());
        for(Task task : scheduler.getProject().getTasks()){
            System.out.println(task);
        }
        System.out.println("-----------------------------------");
    }

    private static Project getSampleProject1() {
        Task wearShirt = new Task("Wear shirt", 1);
        Task wearPants = new Task("Wear pants", 2);
        Task wearUndies = new Task("Wear undies", 1);
        Task fixHair = new Task("Fix hair", 3);
        Task wearSocks = new Task("Wear socks", 1);
        Task wearShoes = new Task("Wear shoes", 2);
        Task wearJacket = new Task("Wear Jacket", 2);
        Task applyPerfume = new Task("Apply Perfume", 1);
        Task goOut = new Task("GO OUT", 3);
        Task wearWatch = new Task("Wear watch", 1);

        wearShoes.addSubTask(wearSocks); // 
        fixHair.addSubTask(wearJacket);
        fixHair.addSubTask(wearShirt);
        wearPants.addSubTask(wearUndies);
        wearSocks.addSubTask(wearPants);
        applyPerfume.addSubTask(wearShirt);
        applyPerfume.addSubTask(wearJacket);
        wearJacket.addSubTask(wearShirt);
        goOut.addSubTask(wearShoes);
        goOut.addSubTask(applyPerfume);
        goOut.addSubTask(wearJacket);
        wearWatch.addSubTask(wearJacket);

        List<Task> tasks = Arrays.asList(wearUndies, wearShirt, applyPerfume, wearWatch,  wearPants, goOut, fixHair, wearShoes, wearSocks, wearJacket);

        return new Project("How to get dressed", tasks);
    }
    
}