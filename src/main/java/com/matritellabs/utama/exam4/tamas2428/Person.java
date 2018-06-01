package com.matritellabs.utama.exam4.tamas2428;

import java.util.Date;

public class Person {
    //private fields for the class
    private String name;
    private Date birthday;
    private String mothersName;
    private String address;

    //constructor
    public Person(String inputName, Date inputBirthday, String inputMothersName, String inputAddress) {
        name = inputName;
        birthday = inputBirthday;
        mothersName = inputMothersName;
        address = inputAddress;
    }

    //getters and setters for the fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
