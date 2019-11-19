package Project.model;

import java.time.LocalDate;

//import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;


import Project.model.Project;

/**
 * Model class for a User information.
 *
 * @author Yongsheng Qian
 */


public class UserInfo {
	
	private final StringProperty UserName;
	private final StringProperty PassWord;
	private final ListProperty<Project> UserProjectList;
	
	private final ObjectProperty<LocalDate> UserCreatedDate;
	private final ObjectProperty<LocalDate> UserLastModifyDate;
	

//	private final AnchorPane TaskPane;
	/**
	 * Default constructor.
	 */
//	public UserInfo() {
//		this(null);
//	}
	
    /**
     * Constructor with some initial data.
     * 
     * @param ProjectName
     */
	public UserInfo() {
		this.UserName = new SimpleStringProperty();
		this.PassWord = new SimpleStringProperty();
		
		this.UserCreatedDate = new SimpleObjectProperty<LocalDate>();
		SetUserCreatedDate();
		
		this.UserLastModifyDate = new SimpleObjectProperty<LocalDate>();
		SetUserLastModifyDate();
		
		ObservableList<Project> observableList = FXCollections.observableArrayList();
		this.UserProjectList = new SimpleListProperty<Project>(observableList);
		
	}

	/*
	 * User Name
	 */
	public String getUserName() {
		return UserName.get();
	}
	
	public void setUserName(String UserName) {
		this.UserName.set(UserName);
	}
	
	public StringProperty UserNameProperty() {
		return UserName;
	}	
	
	/*
	 * Password
	 */
	
	public boolean checkPassWord(StringProperty PassWord) {
		return this.PassWord.get() == PassWord.get();
	}
	
	public boolean setPassWord(String password) {
		 if (this.PassWord != null) {
			 if (this.PassWord.get() != null) {
				 this.PassWord.set(password);
				 return true;
			 }
		 }
		 return false;
	}
	
	
	
	public ObservableList<Project> getUserProjectList() {
		return UserProjectList.get();
	}
	
    public void setProjectList(ObservableList<Project> UserProjectList) {
        this.UserProjectList.set(UserProjectList);
    }
    
    public void addProject(Project UserProject) {
        this.UserProjectList.add(UserProject);
    }
    
    public ListProperty<Project> UserprojectListProperty() {
        return UserProjectList;
    }

	
	
    private void SetUserCreatedDate() {
    	this.UserCreatedDate.set(LocalDate.now());
    }
    
    public LocalDate getUserCreatedDate() {
    	return this.UserCreatedDate.get();
    }
    
    public ObjectProperty<LocalDate> UserCreatedDateProperty() {
    	return UserCreatedDate;
    }
	
    
    private void SetUserLastModifyDate() {
    	this.UserLastModifyDate.set(LocalDate.now());
    }
    
    public LocalDate getUserLastModifyDate() {
    	return this.UserLastModifyDate.get();
    }
    
    public ObjectProperty<LocalDate> UserLastModifyDateProperty() {
    	return UserLastModifyDate;
    }
	

	
}
