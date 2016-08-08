package services.shared;

import java.awt.*;

/**
 * Responsible for setting up proper position for client window after client was opened
 */
public interface MainFramePositionService {

    /**
     * @return top-left point for opened client frame
     */
    Point getProperFrameLocation();
}