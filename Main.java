import java.util.ArrayList;
class Main{
  ArrayList<Project> myProjects;
  private ArrayList<String[2]> loginInfo
  private String username;
  private String password;
  public Main(){
    this.myProjects = myProjects;
    this.loginInfo = loginInfo;
    this.username=username;
    this.password = password;
  }
  void createNewAccount(String userName, String password){
    for(i=0; i<loginInfo.size();i++){
      if(username.equalTo(loginInfo.get(i)[0])){
        System.out.print("Sorry, that username has been taken");
        return;
      }
      if(password.equalTo(loginInfo.get(i)[1])){
        System.out.print("Sorry, that password has been taken");
        return;
      }
    }
    String[] newLoginInfo = {username,password};
    loginInfo.add(newLoginInfo);
  }
  void login(String userName, String password){
    for(i=0; i<loginInfo.size();i++){
      if(userName.equalTo(loginInfo.get(i)[0]) && password.equalTo(loginInfo.get(i)[1]){
        username =userName;
        loadProjects(username);
        return;
      }
  }
  void loadProjects(String username){
  //have the projects load from a text file into myProjects to be displayed with all the relevant information
  }
  void logout(){
    throws IOException{
      BufferedWriter writer = new BufferedWriter(new FileWriter(username));
     for(i=0; i<myProjects.size();i++){
      writer.write(myProjects.get(i).toStringMeta());
     }
    }
    System.exit;
  }
}
