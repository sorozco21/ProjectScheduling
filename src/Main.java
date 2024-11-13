import entity.Project;
import entity.Task;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Create your own schedule for tasks.");
        List<Project> projects = new ArrayList<>();
        projects.add(getSampleProject1());
        int choice = 0;
        do {
            try {
                System.out.println("""
                    
                    Choose your action:
                    [0] Create Project/Plan
                    [1] View Projects/Plan
                    [2] Scheduler
                    [3] Exit""");
                choice = Integer.parseInt(getInput("Enter your choice: "));

                if (choice == 0) {
                    System.out.println("---------CREATING PROJECT/PLAN---------");
                    Project currProject = new Project();
                    currProject.setProjectName(getInput("Enter project/plan name: "));
                    projects.add(currProject);
                    int taskCount = Integer.parseInt(getInput("How many task: ")) ;
                    for(int i=taskCount; i>0; i--){
                        createTask(currProject);
                    }
                    System.out.println("**Project added successfully.");
                    System.out.println("---------CREATING PROJECT/PLAN---------");
                } else if (choice == 1) {
                    System.out.println("---------VIEWING PROJECT/PLAN---------");
                    if (projects.isEmpty()) {
                        System.out.println("**No projects/plan available.");
                    } else {
                        System.out.println(projects.stream()
                                .map(Project::toString)
                                .collect(Collectors.joining("\n\n")));
                    }
                    System.out.println("---------VIEWING PROJECT/PLAN---------");
                } else if (choice==2) {
                    System.out.println("---------SCHEDULER---------");
                    String projName = getInput("Enter project/plan name to schedule: ");
                    Optional<Project> projToSched = projects.stream()
                            .filter(p -> p.getProjectName().equalsIgnoreCase(projName))
                            .findFirst();
                    if (projToSched.isEmpty()) {
                        System.out.println("**Project/plan not found.");
                    }else{
                        Scheduler scheduler = new Scheduler(projToSched.get());
                        scheduler.schedule();
                        System.out.printf("SCHEDULE FOR %s\n", scheduler.getProject().getProjectName());
                        scheduler.getProject().getTasks()
                                .forEach(System.out::println);
                    }
                    System.out.println("---------SCHEDULER---------");
                }
            } catch (NumberFormatException e) {
                System.out.println("**Invalid input. Please enter an integer.");
            } catch (Exception e1) {
                System.out.println("**Unexpected error occurred." + e1.getMessage());
            }
        } while (choice != 3);

        sc.close();
        System.out.println("Exiting the program. Goodbye!");
    }
    private static void createTask(Project project){
        Task task = new Task();
        task.setTaskName(getInput("Enter Task Name: "));
        task.setDuration(Integer.parseInt(getInput("Enter Task Duration: ")));
        project.addTask(task);
    }

    private static String getInput(String description){
        System.out.print(description);
        return sc.nextLine();
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

        List<Task> tasks = Arrays.asList(applyPerfume, wearUndies, wearShirt, wearWatch,  wearPants, goOut, fixHair, wearShoes, wearSocks, wearJacket);

        return new Project("How to get dressed", tasks);
    }
    
}