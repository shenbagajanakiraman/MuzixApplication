package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.repository.TrackReopsitory;

import java.util.List;

public interface TrackService  {

    public List<Track> getAllTracks();

    public void saveTrack(Track track);

    public void deleteTrack(int id);

}
