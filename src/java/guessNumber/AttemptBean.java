/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServlet;

/**
 * 
 * @author Renee
 * with help from http://www.java-programming.info/tutorial/pdf/java/10-JDBC.pdf
 */

@ManagedBean(name = "Attempt")
//@SessionScoped

public class AttemptBean extends HttpServlet implements Serializable {
    private String name;
    private int rating;
    private String classes;
    private boolean hot;
    private String comments;
    private String username;
    private Timestamp atime;
    
    private final String protocol = "jdbc:derby:";
    private final String db = "ics415";
    private final String password = "ics415";
    private final String host = "localhost";
    private final String port = "1527";
    private final String userName = "ICS415";
    private Properties properties;
    
    Attempt attempt;
    ArrayList<Attempt> list;
    
    Connection connection;
    Statement statement;
    ResultSet result;
    String template;
    String derby;
    
    public ArrayList<Attempt> getAttemptList() {
        try {
            list = new ArrayList<Attempt>();
            connection = getConnection();
            statement = connection.createStatement();
            template = "SELECT * FROM ICS415.APP "
                    + "ORDER BY atime DESC";
            result = statement.executeQuery(template);
        
            while(result.next()){
                attempt= new Attempt();  
                atime = result.getTimestamp("atime");
                attempt.setAtime(atime);
                name = result.getString("name");
                attempt.setName(name);
                rating = result.getInt("rating");
                attempt.setRating(rating);
                classes = result.getString("classes");
                attempt.setClasses(classes);                
                hot = result.getBoolean("hot");
                attempt.setHot(hot);
                comments = result.getString("comments");
                attempt.setComments(comments);
                username = result.getString("username");
                attempt.setUsername(username);
                list.add(attempt);
                System.out.print("Thank you: " + username);
                System.out.print("This is the TA's name: " + name);
                System.out.print("This is the TA's rating: " + rating);
                System.out.print("This is the TA's class: " + classes);
                System.out.print("Here are the comments about the TA: " + comments);
            }
            statement.close();
            connection.close();
        }
        catch(Exception e) {
            System.err.println("Error" +e);
        }
        return list;
    }
    
    protected Connection getConnection() throws Exception {
        properties = new Properties();
        properties.put("user", userName);
        properties.put("password", password);
        
        derby = protocol + "//" +host+ ":" +port+ "/" + db + ";create=true";
        connection = DriverManager.getConnection(derby, properties);
        
        return (connection);  
    }
    
}
