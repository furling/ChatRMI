/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

/**
 *
 * @author moro
 */
public class User {
    private static int seed = 1;
    
    private int id;
    private String name;
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public synchronized int getNextId() {
        return seed++;
    }

    public User (String name) {
        this.name = name;
        this.id = this.getNextId();
    }
    
    @Override
    public String toString() {
        return this.name + " (" + this.id + ")";
    }
}
