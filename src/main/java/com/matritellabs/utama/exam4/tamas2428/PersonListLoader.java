package com.matritellabs.utama.exam4.tamas2428;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
                //Iterator for String array index, and the String array, to store data about the person
                int iteratorForArrayIndex = 0;
                String[] personDataArrayForOneDataLine = new String[iteratorForArrayIndex+3];
                while((tempLineForOneDataLine = fileContentIn.readLine()) != null || iteratorForArrayIndex % 4 == 0) {
                    personDataArrayForOneDataLine[iteratorForArrayIndex] = tempLineForOneDataLine;
                    iteratorForArrayIndex++;
                }
                //have to increase to 5, to keep separately the Persons, and be able to write more than 1 only
                iteratorForArrayIndex++;
                //need to parse the birthDate from String, to Date
                DateFormat dateForm = new SimpleDateFormat(personDataArrayForOneDataLine[iteratorForArrayIndex+1]);
                Date birthDate = dateForm.parse(personDataArrayForOneDataLine[iteratorForArrayIndex+1]);
                listToReturn.add(new Person(personDataArrayForOneDataLine[iteratorForArrayIndex],
                        birthDate,
                        personDataArrayForOneDataLine[iteratorForArrayIndex+2],
                        personDataArrayForOneDataLine[iteratorForArrayIndex+3]));

                //else part in case of CSV files, semicolon separation
            } else if(fileFormat.equals(PersonFileFormat.CSV)) {
                int iteratorForArrayIndex;
                if(listToReturn.size() == 0) {
                    iteratorForArrayIndex = 0;
                } else {
                    iteratorForArrayIndex = listToReturn.size()+3;
                }
                //temporary String which need to be splitted
                String tempLineForCSV = fileContentIn.readLine();
                //String array, to store data about the person
                String[] personDataArrayForCSV = tempLineForCSV.split(";") ;
                //need to parse the birthDateForCSV from String, to Date
                DateFormat dateForm = new SimpleDateFormat(personDataArrayForCSV[iteratorForArrayIndex+1]);
                Date birthDateForCSV = dateForm.parse(personDataArrayForCSV[iteratorForArrayIndex+1]);
                listToReturn.add(new Person(personDataArrayForCSV[iteratorForArrayIndex],
                        birthDateForCSV,
                        personDataArrayForCSV[iteratorForArrayIndex+2],
                        personDataArrayForCSV[iteratorForArrayIndex+3]));
            }

        } catch (ParseException | IOException mue) {
            mue.printStackTrace();
        }
        return listToReturn;
    }

    /**
     * The method should write the person list. Depending on the second parameter, the method reads from CSV(semicolon separator),
     * or line separated file.
     */
    public static void writePersonsToFile(String fileURL, PersonFileFormat fileFormat, List<Person> personList) {

        try {
            URL fileAddress = new URL(fileURL);
            URLConnection urlConnect = fileAddress.openConnection();
            urlConnect.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(urlConnect.getOutputStream());
            for(int i = 0; i < personList.size(); i++) {
                if(fileFormat.equals(PersonFileFormat.CSV)) {
                    out.write(personList.get(i).getName() + ";" + personList.get(i).getBirthday() + ";" +
                    personList.get(i).getMothersName() + ";" + personList.get(i).getAddress());
                } else if(fileFormat.equals(PersonFileFormat.ONE_DATA_PER_LINE)) {
                    out.write(personList.get(i).getName() + "\n" + personList.get(i).getBirthday() + "\n" +
                            personList.get(i).getMothersName() + "\n" + personList.get(i).getAddress() + "\n");
                }
            }

        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

}
