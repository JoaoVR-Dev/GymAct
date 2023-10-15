package com.example.GymAct.Models;

public enum UserType {
    STUDENT("student"),
    TEACHER("teacher"),
    ADMIN("admin");

    private String role;

    UserType(String userType){
        this.role = userType;
    }

    public String getUserType(){
        return role;
    }
}
