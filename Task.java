import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Month;
import java.time.LocalDateTime;
class Task {
  private String name;
  private LocalDate dueDate;
  private String description;
  private LocalDateTime created;
  private LocalDateTime lastUpdate;
  private Project project;
  private boolean complete;
  public Task(String name_1, LocalDate dueDate_1, String description_1, Project project){
    this.name = name_1;
    this.dueDate = dueDate_1;
    this.description = description_1;
    this.complete = false;
    this.project = project;
    this.created  = LocalDateTime.now();
  }
  //add subtasks array?
  public Task() {
    this.created = LocalDateTime.now();
  }
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
    project.lastUpdate=LocalDateTime.now();
  }
  public String getDescription(){
    return description;
  }
  public void markAsCompleted(){
    complete = true;
    lastUpdate = LocalDateTime.now();
    project.lastUpdate=LocalDateTime.now();
  }
  public void setProject(Project supertask){
    project = supertask;
  }
  public Project getProject(){
    return project;
  }
  public void setCompletion(boolean completion){
    complete = completion;
  }
  public boolean getCompletion(){
    return complete;
  }
  public String toString(){
    String writtenForm=name+"\n"+dueDate.toString()+"\n"+description;
    return writtenForm;
  }
  public String toStringMeta(){
    String writtenForm="Task\n"+name+"\n"+dueDate.toString()+"\n"+description+"\n"+created.toString()+"\n"+lastUpdate.toString();
    return writtenForm;
  }
}
