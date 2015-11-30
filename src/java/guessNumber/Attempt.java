/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guessNumber;

import java.sql.*;

/**
 *
 * @author Renee
 */
public class Attempt {
    
    private String name;
    private int rating;
    private String classes;
    private boolean hot;
    private String comments;
    private String username;
    private Timestamp atime;
 
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
         this.name = name;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
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
        /**
     * @return the atime
     */
    public Timestamp getAtime() {
        return atime;
    }

    /**
     * @param atime the atime to set
     */
    public void setAtime(Timestamp atime) {
        this.atime = atime;
    }
    

}
