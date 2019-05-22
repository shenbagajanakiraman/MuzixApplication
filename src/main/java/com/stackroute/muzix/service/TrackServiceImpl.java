package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
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
    public List<Track> getAllTracks() {
        return trackReopsitory.findAll();
    }

    @Override
    public void saveTrack(Track track) {

        trackReopsitory.save(track);

    }

    @Override
    public void deleteTrack(int id) {
        myTrack = trackReopsitory.getOne(id);

        if(trackReopsitory.existsById(myTrack.getTrackId())) {

            trackReopsitory.deleteById(id);
        }
    }

    @Override
    public void updateTrack(Track track, int id) {

//        track.setTrackId(id);

        myTrack = trackReopsitory.getOne(id);

        track.setTrackId(myTrack.getTrackId());

        track.setTrackName(myTrack.getTrackName());

        trackReopsitory.save(track);




    }
}
