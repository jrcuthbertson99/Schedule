import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Month;
import java.time.LocalDateTime;
class Project  {
  private String name;
  private LocalDate dueDate;
  private String description;
  private LocalDateTime created;
  public LocalDateTime lastUpdate;
  public ArrayList<Task> steps = new ArrayList<Task>();
  public Project(String name_1, LocalDate dueDate_1, String description_1){
    this.name = name_1;
    this.dueDate = dueDate_1;
    this.description = description_1;
    created  = LocalDateTime.now();
    lastUpdate = LocalDateTime.now();
  }
  public Project() {
    this.created = LocalDateTime.now();
  }
  //public LocalDateTime getCreated()
  public void setDueDate(LocalDate newDueDate){
    dueDate = newDueDate;
    lastUpdate = LocalDateTime.now();
  }
  public LocalDate getDueDate(){
    return dueDate;
  }
  public void setName(String newName){
    name = newName;
    lastUpdate = LocalDateTime.now();
  }
  public String getName(){
    return name;
  }
  public void setDescription(String newDescription){
    description = newDescription;
    lastUpdate = LocalDateTime.now();
  }
  public String getDescription(){
    return description;
  }
  public void addTask(Task task){
    steps.add(task);
  }
  public void dropTask(Task task){
    steps.remove(task);
  }
  //needs a getTask(taskname) to print?
  public void dropCompleted(ArrayList<Task> steps){
    for(int i=0;i< steps.size();i++){
      if (steps.get(i).getCompletion()==true){
        steps.remove(i);
      }
    }
  }
  public String toString(){
    String writtenForm=name+"\n"+dueDate.toString()+"\n"+description;
    return writtenForm;
  }
   public String toStringMeta(){
    String writtenForm=name+"\n"+dueDate.toString()+"\n"+description+"\n"+created.toString()+lastUpdated.toString();
    return writtenForm;
  }
}
