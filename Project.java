import java.time.LocalDate;
import java.time.Month;
import java.time.LocalDateTime;


public class SchedableTests {
   public static void main(String[] args) {
     Main firstRun =new Main();
     firstRun.createNewAccount("firstGuy","TestRun");
     firstRun.login("firstGuy", "TestRun");
    Project project= new Project("Project",LocalDate.of(2019, Month.NOVEMBER, 3) ,"Test");
    firstRun.myProjects.add(project);
   project.setDueDate(LocalDate.of(2019, Month.NOVEMBER, 4));
   project.setName("ProjectTest");
   project.setDescription("Testing for project");

   Task first = new Task("First Task",LocalDate.of(2019, Month.NOVEMBER, 3),"task description", project);
   first.setDueDate(LocalDate.of(2019, Month.NOVEMBER, 4));
   first.setName("Task1Test");
   first.setDescription("Testing for tasks");

   Task second = new Task("Second Task",LocalDate.of(2019, Month.NOVEMBER, 3),"task description",project);
   second.setDueDate(LocalDate.of(2019, Month.NOVEMBER, 4));
   second.setName("Task2Test");
   second.setDescription("Testing for tasks again");


   project.addTask(first);
   //completion tests
   if (project.steps.get(0).getCompletion()==true){
      System.out.print("Not yet! We need to finish!");
   }
   project.addTask(second);
   //need to way to set completion of tasks from project?
   //some sort of printinfo
   project.steps.get(1).setCompletion(true);
   if (project.steps.get(1).getCompletion()==false){
      System.out.print("Wym? We're done here.");
   }

   //System.out.printf("Project: %s\nDue: %s\nDescription: %s\n\n",project.getName(),project.getDueDate().toString(),project.getDescription());
   //System.out.printf("Task: \nDue: %s\nDescripiton: %s\n",first.getName(),first.getDueDate().toString(),first.getDescription());
   System.out.printf(project.toString());
   System.out.printf(project.toStringMeta());
   firstRun.logout();
   }
}
