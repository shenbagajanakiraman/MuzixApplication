package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TrackService  {

    public List<Track> getAllTracks();

    public void saveTrack(Track track);

    public void deleteTrack(int id);

    public void updateTrack(Track track, int id);
}
