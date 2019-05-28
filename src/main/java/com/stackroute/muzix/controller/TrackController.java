package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;

import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController

@PropertySource("application.properties")

@RequestMapping(value = "/api/v1")
//@Api(value = "musicDB", description = "Operations Pertaining to Music")

public class TrackController {

    @Autowired
    private TrackService musicService;



    //Handler Methods

    @PostMapping("/track")
    public ResponseEntity<String> saveTrack (@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        try {
            musicService.saveTrack(track);
            responseEntity = new ResponseEntity<Track>(track, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
//            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
            throw e;
        }
        return responseEntity;
    }


    @PatchMapping("/track/{id}")
    public ResponseEntity<String> updateTrack (@RequestBody Track track,@PathVariable int id) {
        ResponseEntity responseEntity;
        try {


            musicService.updateTrack(track,id);


            responseEntity = new ResponseEntity<Track>(track, HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @PutMapping("/track")
    public ResponseEntity<String> addTrack (@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
             musicService.saveTrack(track);

            responseEntity = new ResponseEntity<Track>(track, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }





    @PostMapping("/track/{id}")
    public ResponseEntity<String> addTrack (@RequestBody Track track,@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            track.setTrackId(id);
            musicService.saveTrack(track);

            responseEntity = new ResponseEntity<Track>(track, HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/track/{id}")
    public ResponseEntity<?> getbyIdGiven (@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
              return new ResponseEntity<Optional<Track>>( musicService.getById(id) , HttpStatus.OK);
        }
        catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("/trackName/{trackName}")
    public ResponseEntity<List<Track>> getByName (@PathVariable String trackName) {
        ResponseEntity responseEntity;
        try {

            return new ResponseEntity<List<Track>>( musicService.getByName(trackName) , HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


       @GetMapping("/tracks")

    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(musicService.getAllTracks(), HttpStatus.OK);
    }


    @Value("${del-msg}")
    private String delMsg;


    @DeleteMapping ("/track/{id}")
    public ResponseEntity<String> deleteTrack (@PathVariable int id) {

        ResponseEntity responseEntity;

        try {
            musicService.deleteTrack(id);
            responseEntity = new ResponseEntity<String>(delMsg, HttpStatus.OK);
        } catch (TrackNotFoundException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



    @Autowired
    Environment env;





    @DeleteMapping ("/tracks")
    public ResponseEntity<String> deleteTracks () {

        ResponseEntity responseEntity;

        try {
            musicService.deleteTracks();
            responseEntity = new ResponseEntity<String>(env.getProperty("del-msg"),HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



}

