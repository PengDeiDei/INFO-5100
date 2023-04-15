package edu.haon.model;

public enum UserType {
    Admin("Admin",0), Student("Student",1);
    private String name;
    private int index;

    private UserType(String name, int index){
        this.name = name;
        this.index = index;
    }

    public String getName(){
        return name;
    }

    public int getIndex(){
        return index;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIndex(int index){
        this.index = index;
    }

}
