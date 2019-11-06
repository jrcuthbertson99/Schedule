import java.util.ArrayList;
import java.io.*;
import java.util.*;
class Main{
  ArrayList<Project> myProjects;
  private ArrayList<String[]> loginInfo;
  private String username;
  private String password;
  public Main(){
    this.myProjects = new ArrayList<Project>();
    this.loginInfo = new ArrayList<String[]>();
    this.username = username;
    this.password = password;
  }
  void createNewAccount(String userName, String password){
    for(int i=0; i<loginInfo.size();i++){
      if(username.equals(loginInfo.get(i)[0])){
        System.out.print("Sorry, that username has been taken");
        return;
      }
      if(password.equals(loginInfo.get(i)[1])){
        System.out.print("Sorry, that password has been taken");
        return;
      }
    }
    String[] newLoginInfo = {userName,password};
    loginInfo.add(newLoginInfo);
  }
  void login(String userName, String password){
    for(int i=0; i<loginInfo.size();i++){
      if(userName.equals(loginInfo.get(i)[0]) && password.equals(loginInfo.get(i)[1])){
        username =userName;
        loadProjects(username);
        return;
      }
   }
 }
 void loadProjects(String user){
   return;
 }
 void assign(String user, Task task){
   Project assignedP=new Project(task.getProject().getName(),task.getProject().getDueDate(),task.getProject().getDescription());
   try{
     BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+File.separator+user));
     writer.write(assignedP.toStringMeta());
     writer.close();
   }catch(IOException e){
       System.out.print("Error:"+ e);
     }
 }
  void logout(){
    try{
      File file =new File(System.getProperty("user.dir")+File.separator+username);
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
     for(int i=0; i<myProjects.size();i++){
      writer.write(myProjects.get(i).toStringMeta());
    }
    writer.close();
  }catch(IOException e){
      System.out.print("Error:"+ e);
    }
    System.exit(0);
  }
}
