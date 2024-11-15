import entity.Project;
import entity.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Create your own schedule for tasks.");
        List<Project> projects = new ArrayList<>();
        projects.add(getSampleProject1());
        projects.add(getSampleProject2());
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
                    String projectName = getInput("Enter project/plan name: ");
                    if (projects.stream().anyMatch(p -> p.getProjectName().equalsIgnoreCase(projectName))) {
                        System.out.println("**Project with this name already exists. Choose a different name.");
                        continue;
                    }

                    currProject.setProjectName(projectName);
                    currProject.setStartDate(LocalDate.parse(getInput("Enter project start date in this format (yyyy-MM-dd):")));
                    int taskCount = Integer.parseInt(getInput("How many task: ")) ;
                    do {
                        Task task = createTask();
                        if (currProject.getTasks().stream().anyMatch(t -> t.getTaskName().equalsIgnoreCase(task.getTaskName()))) {
                            System.out.println("**Task with this name already exists. Choose a different name.");
                        }else{
                            currProject.addTask(task);
                            taskCount--;
                        }
                    }while(taskCount>0);

                    addSubTask(currProject);
                    projects.add(currProject);
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
                        System.out.printf("SCHEDULE FOR : %s\n", scheduler.getProject().getProjectWithStartEnd());
                        scheduler.getProject().getTasks()
                                .forEach(System.out::println);
                    }
                    System.out.println("---------SCHEDULER---------");
                }
            } catch (NumberFormatException e) {
                System.out.println("**Invalid input. Please enter an integer.");
            } catch (DateTimeParseException de){
                System.out.println("**Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            } catch (Exception e1) {
                System.out.println("**Unexpected error occurred." + e1.getMessage());
            }
        } while (choice != 3);

        sc.close();
        System.out.println("Exiting the program. Goodbye!");
    }
    private static Task createTask(){
        Task task = new Task();
        task.setTaskName(getInput("Enter Task Name: "));
        task.setDuration(Integer.parseInt(getInput("Enter Task Duration: ")));
        return task;
    }

    private static void addSubTask(Project project) {
        String choice;
        do {
            choice = getInput("Add Task Dependencies? (y/n): ");

            if (!choice.equalsIgnoreCase("y")) {
                break;
            }

            String mainTaskName = getInput("Enter Task Name to add dependencies: ");
            String subTaskName = getInput("Enter the name of the subtask to add as a dependency: ");

            project.addSubTask(mainTaskName, subTaskName);
        } while (choice.equalsIgnoreCase("y"));
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

    private static Project getSampleProject2(){
        //linear
        Task plan = new Task("Planning", 5);
        Task design = new Task("Design", 4);
        Task develop = new Task("Develop", 25);
        Task test = new Task("Testing", 10);
        Task deploy = new Task("Deploy", 3);
        Task review = new Task("Review", 2);

        review.addSubTask(deploy);
        deploy.addSubTask(test);
        test.addSubTask(develop);
        develop.addSubTask(design);
        design.addSubTask(plan);
        return new Project("Sprint 1", Arrays.asList(review,test,deploy,develop,design,plan));
    }
}