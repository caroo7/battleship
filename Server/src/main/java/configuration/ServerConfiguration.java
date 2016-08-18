package configuration;

import com.sun.net.httpserver.HttpHandler;
import gameLogic.Board;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerServiceExporter;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;
import services.shared.*;
import services.shared.board.BoardsMessageServiceImpl;
import services.shared.board.ShootServiceImpl;
import services.shared.gameFlow.EndGameServiceImpl;
import services.shared.gameFlow.GameInitServiceImpl;
import services.shared.gameFlow.MainFramePositionServiceImpl;
import services.shared.players.PlayerRegistrationServiceImpl;
import services.shared.ships.ShipGeneratorServiceImpl;
import services.undisclosed.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfiguration {

    @Bean
    public SimpleHttpServerFactoryBean serverFactoryBean() {
        SimpleHttpServerFactoryBean httpServerFactoryBean = new SimpleHttpServerFactoryBean();
        httpServerFactoryBean.setPort(Config.SERVER_PORT);

        Map<String, HttpHandler> httpHandlers = new HashMap<>();
        httpHandlers.put(Config.SHIP_GENERATOR_SERVICE, shipGeneratorHttpInvokerServiceExporter());
        httpHandlers.put(Config.BOARD_MESSAGE_SERVICE, boardMessageHttpInvokerServiceExporter());
        httpHandlers.put(Config.PLAYER_REGISTRATION_SERVICE, playerRegistrationHttpInvokerServiceExporter());
        httpHandlers.put(Config.GAME_INIT_SERVICE, gameInitHttpInvokerServiceExporter());
        httpHandlers.put(Config.SHOOT_SERVICE, shootServiceHttpInvokerServiceExporter());
        httpHandlers.put(Config.END_GAME_SERVICE, endGameServiceHttpInvokerServiceExporter());
        httpHandlers.put(Config.MAIN_FRAME_POSITION_SERVICE, mainFramePositionServiceHttpInvokerServiceExporter());
        httpServerFactoryBean.setContexts(httpHandlers);

        return httpServerFactoryBean;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter boardMessageHttpInvokerServiceExporter() {
        return prepareAppropriateServiceHttpInvokerServiceExporter(BoardsMessageService.class, boardMessageService());
    }

    @Bean
    public SimpleHttpInvokerServiceExporter playerRegistrationHttpInvokerServiceExporter() {
        return prepareAppropriateServiceHttpInvokerServiceExporter(PlayerRegistrationService.class, playerRegistrationService());
    }

    @Bean
    public SimpleHttpInvokerServiceExporter shipGeneratorHttpInvokerServiceExporter() {
        return prepareAppropriateServiceHttpInvokerServiceExporter(ShipGeneratorService.class, shipGeneratorService());
    }

    @Bean
    public SimpleHttpInvokerServiceExporter gameInitHttpInvokerServiceExporter() {
        return prepareAppropriateServiceHttpInvokerServiceExporter(GameInitService.class, gameInitService());
    }

    @Bean
    public SimpleHttpInvokerServiceExporter shootServiceHttpInvokerServiceExporter() {
        return prepareAppropriateServiceHttpInvokerServiceExporter(ShootService.class, shootService());
    }

    @Bean
    public SimpleHttpInvokerServiceExporter endGameServiceHttpInvokerServiceExporter() {
        return prepareAppropriateServiceHttpInvokerServiceExporter(EndGameService.class, endGameService());
    }


    @Bean
    public SimpleHttpInvokerServiceExporter mainFramePositionServiceHttpInvokerServiceExporter() {
        return prepareAppropriateServiceHttpInvokerServiceExporter(MainFramePositionService.class, mainFramePositionService());
    }

    private SimpleHttpInvokerServiceExporter prepareAppropriateServiceHttpInvokerServiceExporter(Class serviceClass, Object serviceImpl) {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(serviceClass);
        simpleHttpInvokerServiceExporter.setService(serviceImpl);
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public BoardsMessageService boardMessageService() {
        return new BoardsMessageServiceImpl();
    }

    @Bean
    public PlayerRegistrationService playerRegistrationService() {
        return new PlayerRegistrationServiceImpl();
    }

    @Bean
    public ActualPlayerServiceImpl actualPlayerService() {
        return new ActualPlayerServiceImpl();
    }

    @Bean
    public GameAvailableServiceImpl gameAvailableService() {
        return new GameAvailableServiceImpl();
    }

    @Bean
    public ShipGeneratorService shipGeneratorService() {
        return new ShipGeneratorServiceImpl();
    }

    @Bean
    public BoardStateServiceImpl boardStateService() {
        return new BoardStateServiceImpl();
    }

    @Bean
    public GameInitService gameInitService() {
        return new GameInitServiceImpl();
    }

    @Bean
    public ShootService shootService() {
        return new ShootServiceImpl();
    }

    @Bean
    public AliveShipsServiceImpl aliveShipsService() {
        return new AliveShipsServiceImpl();
    }

    @Bean
    public EndGameService endGameService() {
        return new EndGameServiceImpl();
    }

    @Bean
    public MainFramePositionService mainFramePositionService() {
        return new MainFramePositionServiceImpl();
    }

    @Bean
    public Board firstPlayerBoard() {
        return new Board();
    }

    @Bean
    public Board secondPlayerBoard() {
        return new Board();
    }

}