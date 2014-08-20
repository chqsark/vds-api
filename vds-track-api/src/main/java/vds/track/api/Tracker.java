package vds.track.api;

import org.springframework.integration.annotation.Header;
import org.springframework.integration.annotation.Payload;

public interface Tracker {
    void track(@Header("messageKey") String Id, @Payload String message);
}
