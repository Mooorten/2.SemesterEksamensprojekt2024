package com.example2semestereksamensprojekt2024.model;

public class Member extends Person {
    private Long memberid;

    private String phone;

    private String weight;

    private String height;

    private int age;

    private String gender;

    private String goals;

    private String activitylevel;

    public Member() {
    }

    public Member(Long memberid, String phone, String weight, String height, int age, String gender, String goals, String activitylevel) {
        this.memberid = memberid;
        this.phone = phone;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.goals = goals;
        this.activitylevel = activitylevel;
    }

    public Member(String name, String surname, String email, String password, Long memberid, String phone, String weight, String height, int age, String gender, String goals, String activitylevel) {
        super(name, surname, email, password);
        this.memberid = memberid;
        this.phone = phone;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        this.goals = goals;
        this.activitylevel = activitylevel;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    @Override
    public String toString() {
        return "Member{" +
                "memberid=" + memberid +
                ", phone='" + phone + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", goals='" + goals + '\'' +
                ", activitylevel='" + activitylevel + '\'' +
                '}';
    }
}
