package com.example2semestereksamensprojekt2024.model;

public class Admin extends Person {
    private Long adminid;

    private String role;

    public Admin(Long adminid, String role) {
        this.adminid = adminid;
        this.role = role;
    }

    public Admin(String name, String surname, String email, String password, Long adminid, String role) {
        super(name, surname, email, password);
        this.adminid = adminid;
        this.role = role;
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                ", role='" + role + '\'' +
                '}';
    }
}
