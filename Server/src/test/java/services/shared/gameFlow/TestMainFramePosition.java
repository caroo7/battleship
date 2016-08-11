package services.shared.gameFlow;

import configuration.Config;
import org.testng.annotations.Test;
import services.shared.MainFramePositionService;

import java.awt.*;

import static org.testng.Assert.assertEquals;

public class TestMainFramePosition {
    private MainFramePositionService mainFramePositionService = new MainFramePositionServiceImpl();

    @Test
    void testMainFrameLocationForPlayers() {
        //Given
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int widthGapBetweenFrames = (screenSize.width - 2 * Config.MAIN_FRAME_WIDTH) / 3;
        int heightGapBetweenFrames = (screenSize.height - Config.MAIN_FRAME_HEIGHT) / 2;
        Point expectedLocationForFirstPlayer = new Point(widthGapBetweenFrames, heightGapBetweenFrames);
        Point expectedLocationForSecondPlayer = new Point(2 * widthGapBetweenFrames + Config.MAIN_FRAME_WIDTH, heightGapBetweenFrames);

        //When
        Point actualLocationForFirstPlayer = mainFramePositionService.getProperFrameLocation();
        Point actualLocationForSecondPlayer = mainFramePositionService.getProperFrameLocation();


        //Then
        assertEquals(actualLocationForFirstPlayer, expectedLocationForFirstPlayer);
        assertEquals(actualLocationForSecondPlayer, expectedLocationForSecondPlayer);
    }
}
