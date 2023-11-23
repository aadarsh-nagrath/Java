import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

class Task {
    private String name;
    private LocalDate dueDate;
    private String description;
    private boolean isComplete;

    public Task(String name, LocalDate dueDate, String description) {
        this.name = name;
        this.dueDate = dueDate;
        this.isComplete = false;
        this.description = description;
    }

    public String getName(){
        return name;
    }
    public LocalDate getDuDate(){
        return dueDate;
    }
    public String getDescription(){
        return description;
    }
    public boolean getComplete(){
        return isComplete;
    }

}

class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this .tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public List<Task> getAlltask(){
        return tasks;
    }
}

public class TaskManagement {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scan = new Scanner(System.in);

        System.out.println("Add a task");

        while(true){
            System.out.println("Enter task name: ");
            String name = scan.nextLine();
            System.out.println("Enter task description: ");
            String description = scan.nextLine();
            System.out.println("Enter task due date: (yyyy-mm-dd) ");
            String dueDate = scan.nextLine();
            try {
                LocalDate.parse(dueDate);
            } catch (Exception e) {
                System.out.println("Invalid date format, please try again");
                continue;
            }

            Task newtask = new Task(name, LocalDate.parse(dueDate), description);
            taskManager.addTask(newtask);

            System.out.println("would you like to add another task? (y/n)");
            String answer = scan.nextLine();
            if(answer.equals("n")){
                break;
            }
        }

        System.out.println("All tasks: ");
        System.out.println("--------------------");

        for(Task task : taskManager.getAlltask()){
            System.out.println("Name: " + task.getName());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Due Date: " + task.getDuDate());
            System.out.println("is it complete:" + task.getComplete());
            System.out.println("--------------------");
        }
    }
}