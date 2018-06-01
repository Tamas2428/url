package com.matritellabs.utama.exam4.tamas2428;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PersonListLoader {

    /**
     * Depending on the second parameter, the method reads from CSV(semicolon separator),
     * or line separated file. If the file cannot be found, throws a java.io.FileNotFoundException.
     * @param fileURL
     * @param fileFormat
     * @return list of persons which contained in the file
     */
    public static List<Person> readPersonsFromFile(String fileURL, PersonFileFormat fileFormat) {
        List<Person> listToReturn = new ArrayList<Person>();
        try {
            URL fileAddress = new URL(fileURL);
            BufferedReader fileContentIn = new BufferedReader(new InputStreamReader(fileAddress.openStream()));

            //NOT WORKS!!!!
            /*//Throw a java.io.FileNotFoundException if the file cannot be found!
            if(fileContentIn.equals(null)) {
                throw new FileNotFoundException();
            }*/

            if(fileFormat.equals(PersonFileFormat.ONE_DATA_PER_LINE)) {
                //temporary String to avoid any skip due to multiple calls in 1 cycle
                String tempLineForOneDataLine = null;
                //String array, to store data about the person | not dynamic
                int iterator = 0;
                String[] personDataArrayForOneDataLine = new String[3];
                Date tempDate = null;
                while((tempLineForOneDataLine = fileContentIn.readLine()) != null) {
                    personDataArrayForOneDataLine[iterator] = tempLineForOneDataLine;
                    iterator++;
                }
                //need to parse the birthDate from String, to Date
                DateFormat dateForm = new SimpleDateFormat(personDataArrayForOneDataLine[1]);
                Date birthDate = dateForm.parse(personDataArrayForOneDataLine[1]);
                listToReturn.add(new Person(personDataArrayForOneDataLine[0], birthDate, personDataArrayForOneDataLine[2], personDataArrayForOneDataLine[3]));

                //else part in case of CSV files, semicolon separation
            } else if(fileFormat.equals(PersonFileFormat.CSV)) {
                //temporary String which need to be splitted
                String tempLineForCSV = fileContentIn.readLine();
                //String array, to store data about the person
                String[] personDataArrayForCSV = tempLineForCSV.split(";") ;
                //need to parse the birthDateForCSV from String, to Date
                DateFormat dateForm = new SimpleDateFormat(personDataArrayForCSV[1]);
                Date birthDateForCSV = dateForm.parse(personDataArrayForCSV[1]);
                listToReturn.add(new Person(personDataArrayForCSV[0], birthDateForCSV, personDataArrayForCSV[2], personDataArrayForCSV[3]));

            }

        } catch (ParseException | IOException mue) {
            mue.printStackTrace();
        }
        return listToReturn;
    }

}
