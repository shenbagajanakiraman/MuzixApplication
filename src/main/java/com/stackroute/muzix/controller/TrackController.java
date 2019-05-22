package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;

import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(value = "/api/v1")

public class TrackController {

    @Autowired
    private TrackService musicService;
//
//    @Autowired
//    public  TrackController (MuzixService musicService) {
//
//        this.musicService = musicService;
//
//    }

    //Handler Methods

    @PostMapping("/track")
    public ResponseEntity<String> saveTrack (@RequestBody Track track) {
        ResponseEntity responseEntity;
        try {
            musicService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfullyu created", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



    @PostMapping("/track/{id}")
    public ResponseEntity<String> addTrack (@RequestBody Track track,@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            musicService.saveTrack(track);
            track.setTrackId(id);
            responseEntity = new ResponseEntity<String>("Successfullyu created", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/tracks")

    public ResponseEntity<?> getAllTracks() {
        return new ResponseEntity<List<Track>>(musicService.getAllTracks(), HttpStatus.OK);
    }


    @DeleteMapping ("/track/{id}")
    public ResponseEntity<String> deleteTrack (@PathVariable int id) {

        ResponseEntity responseEntity;

        try {
            musicService.deleteTrack(id);
            responseEntity = new ResponseEntity<String>("Successfully Deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    }

