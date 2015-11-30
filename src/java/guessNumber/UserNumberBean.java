/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Made with help from http://www.java-programming.info/tutorial/pdf/java/10-JDBC.pdf
 */
package guessNumber;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Renee
 */
@ManagedBean(name = "UserNumberBean")
//@SessionScoped

public class UserNumberBean implements Serializable {
    
    private String name;
    private int rating;
    private String classes;
    private boolean hot;
    private String comments;
    private String username;
    private String passwd;
    private Timestamp atime;
        
    String response;
    String login;
    String guest;
//    String lastResponse;
    FacesContext context;
    HttpSession session;

    
    private final String protocol = "jdbc:derby:";
    private final String db = "ics415";
    private final String password = "ics415";
    private final String host = "localhost";
    private final String port = "1527";
    private final String userName = "ICS415";
    private Properties properties;
    Statement statement;
    Connection connection;
    String derby;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
       
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    /**
     * @return the classes
     */
    public String getClasses() {
        return classes;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(String classes) {
        this.classes = classes;
    }

    /**
     * @return the hot
     */
    public boolean isHot() {
        return hot;
    }
    

    /**
     * @param hot the hot to set
     */
    public void setHot(boolean hot) {
        this.hot = hot;
    }
    
        /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
   
    public String getResponse() {
        
        setAttemptList(name, rating, getClasses(), isHot(), getComments(), getUsername());

            return "Thank you. Your rating for " +name+ " has been added.";
        
    }
    
//    public String getLastResponse() {
//        
//        return "Hi! My name nickname is " +username+ ", and I took " +classes+ "from " +name+ ". This TA deserved a rating of " +rating+ ". Wanna know if " +name+ " is hot? In my opinion, it is " +hot+ ".";
//        
//    }
//    
    public String getLogin() {
        
        if(username.equals("uhmanoa") && passwd.equals("icsstudent")) {
            return "Thank you. You have successfully logged in. "
                    + "<a href=form.xhtml>Please click to proceed to Rate My TA Form</a>";
        }
         
        return getGuest();
              
    }
    
        public String getGuest() {
         
        return "You have entered '" +username+ "' as your username and '" +passwd+ "' as your password."
                + "<br> Sorry, but these are invalid credentials.</br> "
                + "<br><a href=signon.xhtml>Please click to return to the login page.</a></br>";
               
    }
    
    

    public UserNumberBean() {
        System.out.println("TA's name: " + name);
        System.out.println("TA's name: " + rating);
    }
    
    private void setAttemptList(String name, int rating, String classes, boolean hot, String comments, String username){
        try{     
            connection = getConnection();
            statement = connection.createStatement();
            
        String template = "insert into ICS415.APP(atime, name, rating, classes, hot, comments, username) values(CURRENT_TIMESTAMP, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(template);
        Attempt entry = new Attempt();
        entry.setName(name);
        ps.setString(1, name);
        ps.setInt(2, getRating());
        ps.setString(3, getClasses());
        ps.setBoolean(4, isHot()); 
        ps.setString(5, getComments()); 
        ps.setString(6, getUsername()); 
        ps.executeUpdate();
        System.out.println("Inserted values in given database...");
            statement.close();
            connection.close();
        }
        catch(Exception e) {
            System.err.println("Error" +e);
        }
            
    }
    
    protected Connection getConnection() throws Exception {
        
        properties = new Properties();
        properties.put("user", userName);
        properties.put("password", password);
        
        derby = protocol + "//" +host+ ":" +port+ "/" + db + ";create=true";
        connection = DriverManager.getConnection(derby, properties);
        
        return (connection);
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return if credentials are correct
     */
    public boolean getLoginname() {
        if(username.equals("uhmanoa") && passwd.equals("icsstudent")) {
            return true;
        }
        return false;
    }
    
}
