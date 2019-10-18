import java.time;
class Subtask{
  private String name;
  private LocalDate dueDate;
  public Subtask(String name_1, LocalDate dueDate_1){
    this.name = name_1;
    this.dueDate = dueDate_1;
  }
  public void setDueDate(LocalDate newDueDate){
    dueDate = newDueDate;
  }
  public LocalDate getDueDate(){
    return dueDate;
  }
  public void setName(String newName){
    name = newName;
  }
  public String getName(){
    return name;
  }

}
