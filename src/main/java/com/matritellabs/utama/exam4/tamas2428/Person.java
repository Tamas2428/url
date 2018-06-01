package com.matritellabs.utama.exam4.tamas2428;

import java.util.Date;

/**
 * This class represent a Person, with 4 fields: name, birthday, mothersname, and address.
 */
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

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(name.equals(this.name) && birthday.equals(this.birthday) && mothersName.equals(this.mothersName)
                && address.equals(this.address)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int newHash = 19;
        newHash = 33 * newHash + name.hashCode();
        newHash = 33 * newHash + mothersName.hashCode();
        newHash = 33 * newHash + address.hashCode();
        newHash = 33 * newHash + birthday.hashCode();
        return newHash;
    }
}
