package com.stackroute.muzix.exception;

public class TrackNotFoundException extends Exception {

    private String message;

    public TrackNotFoundException() {
        super();
    }

    public TrackNotFoundException(String message) {

        super(message);
//        this.message = message;
    }
}


