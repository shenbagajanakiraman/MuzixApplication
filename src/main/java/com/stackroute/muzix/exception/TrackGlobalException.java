package com.stackroute.muzix.exception;



import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
@ControllerAdvice
public class TrackGlobalException {
    @ExceptionHandler(TrackNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ResponseForError handleResourceNotFound(final TrackNotFoundException exception,
                                            final HttpServletRequest request) {

        ResponseForError error = new ResponseForError();
        error.setErrorMessage(exception.getMessage());
        error.callerID(request.getRequestURI());

        return error;

    }

    @ExceptionHandler(TrackAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ResponseForError handleResourceAlreadyExists(final TrackAlreadyExistsException exception,
                                            final HttpServletRequest request) {

        ResponseForError error = new ResponseForError();
        error.setErrorMessage(exception.getMessage());
        error.callerID(request.getRequestURI());

        return error;

    }
}

