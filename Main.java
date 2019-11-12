import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;
class Main{
  ArrayList<Project> myProjects;
  private ArrayList<String[]> loginInfo;
  public String username;
  private String password;
  public Main(){
    this.myProjects = new ArrayList<Project>();
    this.loginInfo = new ArrayList<String[]>();
    this.username = username;
    this.password = password;
    try{
      File file = new File(System.getProperty("user.dir")+File.separator+"loginInfo.txt");
      Scanner li = new Scanner(file);
      int i=0;
      while(li.hasNext()){
        String[] currentLogin = new String[2];
        currentLogin[0]=li.next();
        currentLogin[1]=li.next();
        loginInfo.add(currentLogin);
        i++;
      }
      li.close();
    }catch(IOException e){
        System.out.print("Error:"+ e);
      }
  }
  void createNewAccount(String userName, String password){
    for(int i=0; i<loginInfo.size();i++){
      if(userName.equals(loginInfo.get(i)[0])){
        System.out.print("Sorry, that username has been taken\n");
        return;
      }
      if(password.equals(loginInfo.get(i)[1])){
        System.out.print("Sorry, that password has been taken\n");
        return;
      }
    }
    String[] newLoginInfo = {userName,password};
    loginInfo.add(newLoginInfo);
    try{
      File file = new File(System.getProperty("user.dir")+"loginInfo");
      BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+File.separator+"loginInfo.txt", true));
      writer.write(newLoginInfo[0]+" "+newLoginInfo[1]+System.lineSeparator());
      writer.close();
    }catch(IOException e){
        System.out.print("Error:"+ e);
      }
  }
  void login(String userName, String password){
    for(int i=0; i<loginInfo.size();i++){
      if(userName.equals(loginInfo.get(i)[0]) && password.equals(loginInfo.get(i)[1])){
        username=userName;
        loadProjects(username);
        return;
      }
      else{
        System.out.print("Sorry, the login info is incorrect");
        System.exit(0);
      }
   }
 }
 void loadProjects(String user){
   try{
     File file =new File(System.getProperty("user.dir")+File.separator+username+".txt");
     Scanner lp = new Scanner(file);
     int p =-1;
     int t = -1;
     while(lp.hasNextLine()){
       String title = lp.nextLine();
       System.out.print(title);
       if(title.equals("Project"+System.lineSeparator())){
         p++;
         t=-1;
         String name = lp.nextLine();
         String deadlineDate = lp.nextLine();
         String description = lp.nextLine();
         String createdDate = lp.nextLine();
         String lastUpdatedDate = lp.nextLine();
         LocalDate pulledDueDate = LocalDate.parse(deadlineDate);
         LocalDateTime pulledCreatedDate = LocalDateTime.parse(createdDate);
         LocalDateTime pulledUpdateDate = LocalDateTime.parse(deadlineDate);
         myProjects.add(new Project(name,pulledDueDate,description));
         myProjects.get(p).setCreated(pulledCreatedDate);
         myProjects.get(p).setLastUpdate(pulledUpdateDate);
       }
       else if (title.equals("Task"+System.lineSeparator())){
         t++;
         String name = lp.nextLine();
         System.out.print(name);
         String deadlineDate = lp.nextLine();
         String description = lp.nextLine();
         String createdDate = lp.nextLine();
         String lastUpdatedDate = lp.nextLine();
         LocalDate pulledDueDate = LocalDate.parse(deadlineDate);
         LocalDateTime pulledCreatedDate = LocalDateTime.parse(createdDate);
         LocalDateTime pulledUpdateDate =  LocalDateTime.parse(deadlineDate);
         myProjects.get(p).steps.add(new Task(name, pulledDueDate, description, myProjects.get(p)));
         myProjects.get(p).steps.get(t).setCreated(pulledCreatedDate);
         myProjects.get(p).steps.get(t).setLastUpdate(pulledUpdateDate);
       }
     }
     lp.close();
   }catch(IOException e){
       System.out.print("Error:"+ e);
     }
   return;
 }
 void assign(String user, Task task){
   Project assignedP=new Project(task.getProject().getName(),task.getProject().getDueDate(),task.getProject().getDescription());
   try{
     BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+File.separator+user+".txt"));
     writer.write(assignedP.toStringMeta());
     writer.close();
   }catch(IOException e){
       System.out.print("Error:"+ e);
     }
 }
  void logout(){
    try{
      File file =new File(System.getProperty("user.dir")+File.separator+username+".txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
     for(int i=0; i<myProjects.size();i++){
      writer.write(myProjects.get(i).toStringMeta());
    }
    writer.close();
  }catch(IOException e){
      System.out.print("Error:"+ e);
    }
    username=null;
    return;

  }
}
