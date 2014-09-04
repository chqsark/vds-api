package vds.track.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackController {
    private final CounterService counterService;
    private final Tracker tracker;

    @Autowired
    public TrackController(CounterService counterService, Tracker tracker) {
        this.counterService = counterService;
        this.tracker = tracker;
    }

    @MessageMapping("/{id}")
    public void track(@DestinationVariable("id") String id, String message) throws Exception {
        counterService.increment("counter.tracker.received");
        tracker.track(id, message);
        counterService.increment("counter.tracker.sent");
    }
}
