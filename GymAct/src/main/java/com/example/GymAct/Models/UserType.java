package com.example.GymAct.Models;

public enum UserType {
    STUDENT("Student"),
    TEACHER("Teacher"),
    ADMIN("Admin");

    private String userType;

    UserType(String userType){
        this.userType = userType;
    }

    public String getUserType(){
        return userType;
    }
}
