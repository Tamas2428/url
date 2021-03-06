package com.matritellabs.utama.exam4.tamas2428;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class ment to read, and write files about Persons through URL address.
 */
public class PersonListLoader {

    public static Logger logger = LoggerFactory.getLogger(PersonListLoader.class);

    /**
     * Depending on the second parameter, the method reads from CSV(semicolon separator),
     * or line separated file. If the file cannot be found, throws a java.io.FileNotFoundException.
     * @param fileURL URL address where the file is
     * @param fileFormat CSV or new line format
     * @return list of persons which contained in the file
     */
    public static List<Person> readPersonsFromFile(String fileURL, PersonFileFormat fileFormat) {
        List<Person> listToReturn = new ArrayList<Person>();
        try {
            URL fileAddress = new URL(fileURL);
            BufferedReader fileContentIn = new BufferedReader(new InputStreamReader(fileAddress.openStream()));

/*            //Throw a java.io.FileNotFoundException if the file cannot be found!
            HttpURLConnection con = (HttpURLConnection)(fileAddress).openConnection();
            //in case of no response, getResponseCode() returns -1, when we throw the exception
            if(con.getResponseCode() == -1) {
                throw new FileNotFoundException();
            }*/

            int iteratorForArrayIndexCSV = 0;

            if(fileFormat.equals(PersonFileFormat.ONE_DATA_PER_LINE)) {
                //temporary String to avoid any skip due to multiple calls in 1 cycle
                String tempLineForOneDataLine = null;
                //Iterator for String array index, and the String array, to store data about the person
                String[] personDataArrayForOneDataLine = new String[iteratorForArrayIndexCSV+3];
                while((tempLineForOneDataLine = fileContentIn.readLine()) != null || iteratorForArrayIndexCSV % iteratorForArrayIndexCSV+4 == 0) {
                    personDataArrayForOneDataLine[iteratorForArrayIndexCSV] = tempLineForOneDataLine;
                    iteratorForArrayIndexCSV++;
                }
                //have to increase to 5, to keep separately the Persons, and be able to write more than 1 only
                iteratorForArrayIndexCSV++;
                //need to parse the birthDate from String, to Date
                DateFormat dateForm = new SimpleDateFormat(personDataArrayForOneDataLine[iteratorForArrayIndexCSV+1]);
                Date birthDate = dateForm.parse(personDataArrayForOneDataLine[iteratorForArrayIndexCSV+1]);
                listToReturn.add(new Person(personDataArrayForOneDataLine[iteratorForArrayIndexCSV],
                        birthDate,
                        personDataArrayForOneDataLine[iteratorForArrayIndexCSV+2],
                        personDataArrayForOneDataLine[iteratorForArrayIndexCSV+3]));
                logger.info("New person added to the List of persons with One data per line format");

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
                //check how many ';' are there
                Pattern semmicolonCheckLine = Pattern.compile(";");
                Matcher matcher = semmicolonCheckLine.matcher(tempLineForCSV);
                //increasing numberOfMatch until it finds
                int numberOfMatch = 0;
                while(matcher.find()) {
                    numberOfMatch++;
                }
                //in case of 4 ';' in 1 row, throws RuntimeException
                if(numberOfMatch == 4) {
                    throw new RuntimeException();
                }
                //String array, to store data about the person
                String[] personDataArrayForCSV = tempLineForCSV.split(";") ;
                //need to parse the birthDateForCSV from String, to Date
                DateFormat dateForm = new SimpleDateFormat(personDataArrayForCSV[iteratorForArrayIndex+1]);
                Date birthDateForCSV = dateForm.parse(personDataArrayForCSV[iteratorForArrayIndex+1]);
                listToReturn.add(new Person(personDataArrayForCSV[iteratorForArrayIndex],
                        birthDateForCSV,
                        personDataArrayForCSV[iteratorForArrayIndex+2],
                        personDataArrayForCSV[iteratorForArrayIndex+3]));
                logger.info("New person added to the List of persons with CSV format");
            }

        } catch (ParseException | IOException mue) {
            mue.printStackTrace();
        }
        return listToReturn;
    }

    /**
     * The method should write the person list. Depending on the second parameter, the method reads from CSV(semicolon separator),
     * or line separated file.
     * @param fileURL URL address where the file is
     * @param fileFormat CSV or new line format
     * @param personList List of persons what we want to write
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
                    personList.get(i).getMothersName() + ";" + personList.get(i).getAddress() + "\n");
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
