/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

public class Course {
    private int id;
    private String name;
    private int room;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Course(int id, String name, int room) {
        this.id = id;
        this.name = name;
        this.room = room;
    }

    public Course() {
    }
     @Override
    public String toString() {
      String a= this.name;
        return a;
    }
    
}
