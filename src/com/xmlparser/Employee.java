package com.xmlparser;

public class Employee {
    private String name;
    private String role;
    private int age;
    private Boolean permanent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    @Override
    public String toString() {
        return "Employee :" +
                " name='" + name + '\'' +
                " role='" + role + '\'' +
                " age=" + age +
                " permanent=" + permanent ;
    }
}
