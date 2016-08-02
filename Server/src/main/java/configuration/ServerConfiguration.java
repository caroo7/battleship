package configuration;

import com.sun.net.httpserver.HttpHandler;
import gameLogic.Board;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerServiceExporter;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;
import services.shared.*;
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
        httpServerFactoryBean.setContexts(httpHandlers);

        return httpServerFactoryBean;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter boardMessageHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(BoardsMessageService.class);
        simpleHttpInvokerServiceExporter.setService(boardMessageService());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter playerRegistrationHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(PlayerRegistrationService.class);
        simpleHttpInvokerServiceExporter.setService(playerRegistrationService());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter shipGeneratorHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(ShipGeneratorService.class);
        simpleHttpInvokerServiceExporter.setService(shipGeneratorService());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter gameInitHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(GameInitService.class);
        simpleHttpInvokerServiceExporter.setService(gameInitService());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter shootServiceHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(ShootService.class);
        simpleHttpInvokerServiceExporter.setService(shootService());
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
    public ActualPlayerService actualPlayerService() {
        return new ActualPlayerServiceImpl();
    }

    @Bean
    public GameAvailableService gameAvailableService() {
        return new GameAvailableServiceImpl();
    }

    @Bean
    public ShipGeneratorService shipGeneratorService() {
        return new ShipGeneratorServiceImpl();
    }

    @Bean
    public BoardStateService boardStateService() {
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
    public AliveShipsService aliveShipsService() {
        return new AliveShipsServiceImpl();
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