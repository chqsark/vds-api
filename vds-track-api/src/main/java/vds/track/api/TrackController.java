package vds.track.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class TrackController {
    private static Logger logger = LoggerFactory.getLogger(TrackController.class);

    @MessageMapping("/dom")
    public void track(String message) throws Exception {
        logger.info(message);
    }
}
