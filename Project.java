import java.time;
import java.util;
import java.time;
class {
  private String name;
  private LocalDate dueDate;
  private String description;
  private LocalDateTime created;
  public LocalDateTime lastUpdate;
  private ArrayList<Task> steps;
  public Project(String name_1, LocalDate dueDate_1, String description_1){
    this.name = name_1;
    this.dueDate = dueDate_1;
    this.description = description_1;
    this.steps = steps;
  }
  public void setDueDate(LocalDate newDueDate){
    dueDate = newDueDate;
    lastUpdate = now();
  }
  public LocalDate getDueDate(){
    return dueDate;
  }
  public void setName(String newName){
    name = newName;
    lastUpdate = now();
  }
  public String getName(){
    return name;
  }
  public void setDescription(String newDescription){
    description = newDescription;
    lastUpdate = now();
  }
  public String getDescription(){
    return description;
  }
  public addTask(String newTaskName, LocalDate newTaskDeadline, String newDescription)
    Task task = new Task(newTaskName, newTaskDeadline, newDescription)
    steps.add(task);
  }
  public dropTask (Task task){
    steps.remove(task);
  }
  public dropCompleted(ArrayList<Task> steps){
    for(i=0;i< steps.size();i++){
      if (steps[i].getCompletion()=true){
        steps.remove(i);
      }
    }
  }
}
