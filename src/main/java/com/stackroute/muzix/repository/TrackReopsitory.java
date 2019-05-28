package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TrackReopsitory extends JpaRepository<Track,Integer> {



    @Query (value = "select * from track where track_name = ?1", nativeQuery = true)
    public List<Track> getByName(String trackName);


}
