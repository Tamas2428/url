package com.matritellabs.utama.exam4.tamas2428;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OwnTest {
    public static void main(String[] args) {
        PersonListLoader test = new PersonListLoader();
        //test.readPersonsFromFile("file:///home/tamas2428/Developement/Projects/exam4/src/main/java/com/matritellabs/utama/exam4/tamas2428/testNewLine.txt", PersonFileFormat.ONE_DATA_PER_LINE);

        List personline = new ArrayList<>();
        personline.add(new Person("a", new Date(12233), "b", "a" ));
        test.writePersonsToFile("file:///home/tamas2428/Developement/Projects/exam4/src/main/java/com/matritellabs/utama/exam4/tamas2428/semicolon.txt", PersonFileFormat.CSV, personline);
    }
}
