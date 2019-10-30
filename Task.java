import java.time;
class {
  private String name;
  private LocalDate dueDate;
  private String description;
  private LocalDateTime created;
  private LocalDateTime lastUpdate; 
  public Task(String name_1, LocalDate dueDate_1, String description_1){
    this.name = name_1;
    this.dueDate = dueDate_1;
    this.description = description_1;
    
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

}
