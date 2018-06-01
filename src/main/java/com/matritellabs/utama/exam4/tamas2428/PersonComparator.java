package com.matritellabs.utama.exam4.tamas2428;

import java.util.Comparator;

/**
 * This class implements Comparator and overrided according to birthday, name and mothersname.
 */
public class PersonComparator implements Comparator {


    /**
     * Compares Person objects based on birth date. If it is the same, it uses name to decide, or mothers name.
     */
    @Override
    public int compare(Object o1, Object o2) {
        Person person1 = (Person)o1;
        Person person2 = (Person)o2;

        int resultOfBirthCompare = person1.getBirthday().compareTo(person2.getBirthday());
        if(resultOfBirthCompare == 0) {
            return 0;
        }
        if(resultOfBirthCompare < 0 || resultOfBirthCompare > 0) {
            if(person1.getName().equals(person2.getName())) {
                if(person1.getMothersName().equals(person2.getMothersName())) {
                    return 0;
                }
            }
            return 1;
        } else {
            return 1;
        }
    }
}

