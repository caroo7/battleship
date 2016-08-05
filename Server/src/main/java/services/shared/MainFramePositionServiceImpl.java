package services.shared;

import configuration.Config;

import java.awt.*;

public class MainFramePositionServiceImpl implements MainFramePositionService {

    private int frameCounter = 0;

    GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private int widthGapBetweenFrames = (graphicsDevice.getDisplayMode().getWidth() - 2 * Config.MAIN_FRAME_WIDTH) / 3;

    private  int heightGapBetweenFrames =(graphicsDevice.getDisplayMode().getHeight()-Config.MAIN_FRAME_HEIGHT)/2;

    @Override
    public Point getProperFrameLocation() {
        frameCounter++;
        return frameCounter == 1 ? new Point(widthGapBetweenFrames, heightGapBetweenFrames) : new Point(2 * widthGapBetweenFrames + Config.MAIN_FRAME_WIDTH, heightGapBetweenFrames);
    }
    
}