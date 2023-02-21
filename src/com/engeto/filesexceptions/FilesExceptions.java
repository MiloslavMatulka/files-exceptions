package com.engeto.filesexceptions;

import java.util.logging.Logger;

public class FilesExceptions {
    private static final Logger logger =
            Logger.getLogger("com.engeto.filesexceptions");

    private static void codeWithException() {
        // Unchecked exception
        throw new RuntimeException("RuntimeException test");
    }

    private static void codeWithException2() throws Exception {
        // Checked exception - must be delegated or caught
        throw new Exception("Exception test");
    }

    private static void printRequest(Request request) {
        System.out.println("Number of passengers: "
                + request.getNumberOfPassengers());
    }

    public static void main(String[] args) /*throws Exception*/ {
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
    }
}
