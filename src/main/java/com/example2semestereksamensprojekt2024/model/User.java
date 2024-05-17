package com.example2semestereksamensprojekt2024.model;

public class User {
    private Long userid;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String weight;
    private String height;
    private int age;
    private String gender;
    private String goals;
    private String activitylevel;
    private String role;
    private double bmr;

    public User() {
    }

    public User(Long userid, String email, String password, String name, String surname, String phone, String weight, String height, String gender, String goals, String activitylevel, String role, int age, double bmr) {
        this.userid = userid;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.goals = goals;
        this.activitylevel = activitylevel;
        this.role = role;
        this.age = age;
        this.bmr = bmr;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getActivitylevel() {
        return activitylevel;
    }

    public void setActivitylevel(String activitylevel) {
        this.activitylevel = activitylevel;
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

    public double getBmr() {
        return bmr;
    }

    public void setBmr(double bmr) {
        this.bmr = bmr;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", gender='" + gender + '\'' +
                ", goals='" + goals + '\'' +
                ", activitylevel='" + activitylevel + '\'' +
                ", role='" + role + '\'' +
                ", age=" + age +
                ", bmr=" + bmr +
                '}';
    }
}