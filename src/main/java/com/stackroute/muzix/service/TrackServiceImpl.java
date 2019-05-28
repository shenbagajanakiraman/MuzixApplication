package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackReopsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class TrackServiceImpl implements TrackService {

    private Track myTrack;
    private TrackReopsitory trackReopsitory;

    @Autowired

    public TrackServiceImpl(TrackReopsitory trackReopsitory) {
        this.trackReopsitory = trackReopsitory;
    }


    @Override
    public void saveTrack(Track track) throws TrackAlreadyExistsException{

        if(trackReopsitory.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("Track Already Exists...");
        }

        trackReopsitory.save(track);
    }


    @Override
    public void updateTrack(Track track, int id) {

//        track.setTrackId(id);

        myTrack = trackReopsitory.getOne(id);

        track.setTrackId(myTrack.getTrackId());

        track.setTrackName(myTrack.getTrackName());

        trackReopsitory.save(track);

    }



    @Override
    public void deleteTrack(int id) throws TrackNotFoundException {
        myTrack = trackReopsitory.getOne(id);

        if(!trackReopsitory.existsById(myTrack.getTrackId())) {
            throw new TrackNotFoundException("Track not Found !!!");
        }

            trackReopsitory.deleteById(id);

    }


    @Override
    public void deleteTracks() {
        trackReopsitory.deleteAllInBatch();
    }

    @Override
    public List<Track> getByName(String trackName) {
        return trackReopsitory.getByName(trackName);
    }

    @Override
    public Optional<Track> getById(int id) throws TrackNotFoundException {

        if(trackReopsitory.existsById(id))
            return trackReopsitory.findById(id);

        throw new TrackNotFoundException("Track is not Found !!!");
    }


    @Override
    public List<Track> getAllTracks() {
        return trackReopsitory.findAll();
    }
}
