package services.shared;

import configuration.Config;

import java.awt.*;

public class MainFramePositionServiceImpl implements MainFramePositionService {

    @Override
    public Point getProperFrameLocation() {
        int frameCounter = 0;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int widthGapBetweenFrames = (screenSize.width - 2 * Config.MAIN_FRAME_WIDTH) / 3;
        int heightGapBetweenFrames = (screenSize.height - Config.MAIN_FRAME_HEIGHT) / 2;
        frameCounter++;
        return frameCounter == 1 ? new Point(widthGapBetweenFrames, heightGapBetweenFrames) : new Point(2 * widthGapBetweenFrames + Config.MAIN_FRAME_WIDTH, heightGapBetweenFrames);
    }

}