package com.kaganmercan.airties.ATMProject.operations;

import com.kaganmercan.airties.ATMProject.management.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

/**
 * @author kaganmercan
 */
public class FileOperations {
    private final static Logger log = LoggerFactory.getLogger(Options.class);

    public static void saveToFile(String text, boolean append) throws IOException {
        // create a file
        File file = new File("./src/main/java/com/kaganmercan/airties/ATMProject/database/customers.txt");
        // create a file writer
        FileWriter fw = new FileWriter(file, append);
        // create a print writer
        PrintWriter pw = new PrintWriter(fw);
        pw.println(text);
        pw.close();
    }

    public static void updateFile(String oldLine, String newLine) throws IOException {
        //Instantiating the File class
        String filePath = "./src/main/java/com/kaganmercan/airties/ATMProject/database/customers.txt";
        //Instantiating the Scanner class to read the file
        Scanner sc = new Scanner(new File(filePath));
        //instantiating the StringBuffer class
        StringBuffer buffer = new StringBuffer();
        //Reading lines of the file and appending them to StringBuffer
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();
        //closing the Scanner object
        sc.close();
        fileContents = fileContents.replace(oldLine, newLine);
        //instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }


    public static String fileReader() {
        String stringReturned = "";
        try {
            //creates a new file instance
            File file = new File("./src/main/java/com/kaganmercan/airties/ATMProject/database/customers.txt");
            //reads the file
            FileReader fileReader = new FileReader(file);
            //creates a buffering character input stream
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //constructs a string buffer with no characters
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);      //appends line to string buffer
                stringBuffer.append("\n");     //line feed
            }
            bufferedReader.close();    //closes the stream and release the resources
            stringReturned = stringBuffer.toString();
        } catch (IOException e) {
            log.warn("No user is registered in database. Please add new account.");
            log.trace("Exception: ", e);
            System.out.println("No user is registered in database. Please add new account.");
        }
        return stringReturned;
    }

    public static String[] stringParser(String string) {
        return string.split("\\r?\\n|\\r");
    }
}

