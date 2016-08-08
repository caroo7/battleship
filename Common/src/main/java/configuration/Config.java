package configuration;

public class Config {

    public static final int MAIN_FRAME_WIDTH = 720;
    public static final int MAIN_FRAME_HEIGHT = 450;
    public static final int BOARD_SIZE = 8;

    static final String HOST_NAME = "127.0.0.1";
    static final int SERVER_PORT = 8088;
    static final String SHIP_GENERATOR_SERVICE = "/http/ShipGeneratorService";
    static final String BOARD_MESSAGE_SERVICE = "/http/BoardsMessageService";
    static final String PLAYER_REGISTRATION_SERVICE = "/http/PlayerRegistrationService";
    static final String GAME_INIT_SERVICE = "/http/GameInitService";
    static final String SHOOT_SERVICE = "/http/ShootService";
    static final String END_GAME_SERVICE = "/http/EndGameService";
    static final String MAIN_FRAME_POSITION_SERVICE = "/http/MainFramePositionService";

}
