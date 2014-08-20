package vds.track.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackController {
    private static Logger logger = LoggerFactory.getLogger(TrackController.class);
    private final Tracker tracker;

    @Autowired
    public TrackController(Tracker tracker) {
        this.tracker = tracker;
    }

    @MessageMapping("/{id}")
    public void track(@DestinationVariable("id") String id, String message) throws Exception {
        tracker.track(id, message);
    }
}
