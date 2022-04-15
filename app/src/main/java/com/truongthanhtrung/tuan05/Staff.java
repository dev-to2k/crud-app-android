package com.truongthanhtrung.tuan05;

import java.util.Date;

public class Staff {
    private String fullName;
    private String dob;
    private String gender;
    private String birthPlace;
    private String salary;

    public Staff(String fullName, String dob, String gender, String birthPlace, String salary) {
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.birthPlace = birthPlace;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
