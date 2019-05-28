package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackService  {

    public List<Track> getAllTracks();

    public void saveTrack(Track track) throws TrackAlreadyExistsException;

    public void deleteTrack(int id) throws TrackNotFoundException;

    public void updateTrack(Track track, int id);

    public Optional<Track> getById(int id) throws TrackNotFoundException;

    public void deleteTracks();

    public List<Track> getByName(String trackName);
}
