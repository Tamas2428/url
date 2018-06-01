package com.matritellabs.utama.exam4.tamas2428;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    //logger to keep track logs
    Logger log = LoggerFactory.getLogger(Person.class);

    //constructor
    public Person(String inputName, Date inputBirthday, String inputMothersName, String inputAddress) {
        name = inputName;
        birthday = inputBirthday;
        mothersName = inputMothersName;
        address = inputAddress;
        log.info("New instance of Person created");
    }

    //getters and setters for the fields
    public String getName() {
        log.info("On: " + this.name + "called getName method");
        return name;
    }

    public void setName(String name) {
        log.info("On: " + this.name + "called setName method");
        this.name = name;
    }

    public Date getBirthday() {
        log.info("On: " + this.name + "called getBirthday method");
        return birthday;
    }

    public void setBirthday(Date birthday) {
        log.info("On: " + this.name + "called setBirthday method");
        this.birthday = birthday;
    }

    public String getMothersName() {
        log.info("On: " + this.name + "called getMothersName method");
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        log.info("On: " + this.name + "called setMothersName method");
        this.mothersName = mothersName;
    }

    public String getAddress() {
        log.info("On: " + this.name + "called getAddres method");
        return address;
    }

    public void setAddress(String address) {
        log.info("On: " + this.name + "called setAddres method");
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
