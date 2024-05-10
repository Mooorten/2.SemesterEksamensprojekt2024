package com.example2semestereksamensprojekt2024.model;

public class Admin extends Person {
    private Long adminid;

    public Admin() {
    }

    public Admin(Long adminid) {
        this.adminid = adminid;
    }

    public Admin(String name, String surname, String email, String password, Long adminid) {
        super(name, surname, email, password);
        this.adminid = adminid;
    }

    public Admin(String name, String surname, String email, String password, String role, Long adminid) {
        super(name, surname, email, password, role);
        this.adminid = adminid;
    }

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminid=" + adminid +
                '}';
    }
}
