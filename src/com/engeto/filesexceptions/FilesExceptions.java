package com.engeto.filesexceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.Scanner;

public class FilesExceptions {
    public static final String OUTPUT_FILE = "text2.txt";
    private static final Logger logger =
            Logger.getLogger("com.engeto.filesexceptions");

    public static int calculateByteSize(int c) {
        int byteSize = 0;
        while (c > 0) {
            byteSize++;
            c /= 256;
        }
        return  byteSize;
    }

    private static void codeWithException() {
        // Unchecked exceptions - descendants of RuntimeException class.
        // Delegation of catching not required, sometimes it is good to catch.
        // (NumberFormatException, DateTimeFormatException,
        // NullPointerException, IndexOutOfBoundsException etc.)
        throw new RuntimeException("RuntimeException test");
    }

    private static void codeWithException2() throws Exception {
        // Checked exceptions
        // - must be delegated or caught
        // - descendants of Exceptions class
        throw new Exception("Exception test");
    }

    // Errors - no sense to catch them, errors of JVM.
    // Descendants of Error class.
    // (OutOfMemoryException, InternalError etc.)

    public static List<Person> importFromFile(/*List<Person> dataToWrite*/)
            throws FileNotFoundException {
        List<Person> personList = new ArrayList<>();
        File dataFile =
                new File("src/" + OUTPUT_FILE).getAbsoluteFile();

        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNextLine()) {
            String record = scanner.nextLine();
            personList.add(Person.parsePerson(record));
        }
        return personList;
    }

    private void parseData(String data) {
        String[] parsedData = data.split(":");
        System.out.println("Name: " + parsedData[0]);
        System.out.println(" height: " + parsedData[1]);
    }

    private static void printRequest(Request request) {
        System.out.println("Number of passengers: "
                + request.getNumberOfPassengers());
    }

    public static void main(String[] args) /*throws Exception*/
            throws IOException {
//        codeWithException();
//        codeWithException2();
        Request request = new Request();

        try {
//            codeWithException2();
//            throw new Exception("Test of the checked exception!");
            request.setNumberOfPassengers(1);
            printRequest(request);
        } catch (Exception ex) {
//            ex.printStackTrace();
//            logger.warning(ex.getMessage());
            System.err.println("Wrong number of passengers: "
                    + ex.getMessage());
        }
        System.out.println("---");

        try (FileReader inputStream = new FileReader(
                new File("src/inputFile.txt").getAbsoluteFile());
             FileWriter outputStream = new FileWriter(
                new File("src/outputFile.txt").getAbsoluteFile());) {
            // Windows Czech coding (852 cmd, 1250 graphical environment and
            // text files, UTF-16 file names in file system).
            System.out.println("Numeric code (bit) - letter - "
                    + "bytes per character");
            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
                String v = String.format("%c", c);
                System.out.println(c + " - " + v + " - "
                        + calculateByteSize(c));
            }
        }
        System.out.println("---");

        File dataFile =
                new File("src/text.txt").getAbsoluteFile();
        Scanner scanner = new Scanner(dataFile);
        while (scanner.hasNext()) {
            String record = scanner.nextLine();
            System.out.println(record);
            String[] words = record.split(", ");
            for (String word : words) {
                System.out.println(word);
            }
        }
        System.out.println("---");

        try {
            List<Person> readFromFile = importFromFile(/*dataToWrite*/);
            readFromFile.stream()
                    .forEach(i -> System.out.println(i.getName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
