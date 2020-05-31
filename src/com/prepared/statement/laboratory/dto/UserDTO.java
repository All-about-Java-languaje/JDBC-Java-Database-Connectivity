package com.prepared.statement.laboratory.dto;

public class UserDTO {

    private int id_user;
    private String name;
    private String lastName;

    public UserDTO(){

    }

    public UserDTO(int id_user, String name, String lastName) {
        this.id_user = id_user;
        this.name = name;
        this.lastName = lastName;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
