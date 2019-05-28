package com.stackroute.muzix.seeddata;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource("application.properties")

@Component
@Data

public class MyCommandLineRunner implements CommandLineRunner {


    Track track = new Track();

    @Autowired
    TrackService trackService;

    @Autowired
            //Use env or Use @Value and specify a String for each values
    Environment env;



    @Override
    public void run(String... args) throws Exception {

        track.setTrackName(env.getProperty("track-name"));
        track.setComments(env.getProperty("track-comments"));
        track.setTrackId(Integer.parseInt(env.getProperty("track-id")));

        trackService.saveTrack(track);

        System.out.println(trackService.getAllTracks());

    }
}
