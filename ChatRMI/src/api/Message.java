/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.Serializable;

/**
 *
 * @author moro
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private User user;
    private String msg;
    private long timestamp;
    
    public String getMsg() {
        return msg;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message(User u, String msg) {
        this.user = u;
        this.msg = msg;
        this.timestamp = System.currentTimeMillis();
    }
    
    @Override
    public String toString() {
        return "[" + this.timestamp + "] " + this.user.getName() + ": " + this.msg;
    }
}
