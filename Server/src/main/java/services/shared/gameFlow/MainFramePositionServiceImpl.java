package services.shared.gameFlow;

import configuration.Config;
import services.shared.MainFramePositionService;

import java.awt.*;

/**
 * Responsible for setting up proper position for client window after client is opened
 */
public class MainFramePositionServiceImpl implements MainFramePositionService {

    private int frameCounter = 0;

    /**
     * @return top-left point calculation for opened client frame - it uses Toolkit to retrieve information about screen size
     */
    @Override
    public Point getProperFrameLocation() {
        Dimension screenSize = getScreenSize();
        int widthGapBetweenFrames = (screenSize.width - 2 * Config.MAIN_FRAME_WIDTH) / 3;
        int heightGapBetweenFrames = (screenSize.height - Config.MAIN_FRAME_HEIGHT) / 2;
        frameCounter++;
        return frameCounter == 1 ? new Point(widthGapBetweenFrames, heightGapBetweenFrames) : new Point(2 * widthGapBetweenFrames + Config.MAIN_FRAME_WIDTH, heightGapBetweenFrames);
    }


    Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}