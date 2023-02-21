package com.engeto.filesexceptions;

public class Request {

    int numberOfPassengers;

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int passengers) throws TaxiException {
        if (passengers < 1) {
            throw new TaxiException("Number of passengers cannot be less "
                    + "than 1 (entered: " + passengers + ")");
        }
        numberOfPassengers = passengers;
    }
}
