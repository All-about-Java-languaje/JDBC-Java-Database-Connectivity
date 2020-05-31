package com.prepared.statement.people.dto;

public class PersonDTO {

    private int id_person;
    private String name;
    private String lastName;

    public PersonDTO() {
    }

    public PersonDTO(int id_person) {
        this.id_person = id_person;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
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

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id_person=" + id_person +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
