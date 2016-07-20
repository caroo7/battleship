package configuration;

import com.sun.net.httpserver.HttpHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerServiceExporter;
import org.springframework.remoting.support.SimpleHttpServerFactoryBean;
import services.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ServerConfiguration {

    @Bean
    public SimpleHttpServerFactoryBean serverFactoryBean() {
        SimpleHttpServerFactoryBean httpServerFactoryBean = new SimpleHttpServerFactoryBean();
        httpServerFactoryBean.setPort(Config.SERVER_PORT);

        Map<String, HttpHandler> httpHandlers = new HashMap<>();
        httpHandlers.put(Config.USER_BOARD_SERVICE, userBoardHttpInvokerServiceExporter());
        httpHandlers.put(Config.PLAYER_IDENTIFIER_SERVICE, playerIdentifierHttpInvokerServiceExporter());
        httpServerFactoryBean.setContexts(httpHandlers);

        return httpServerFactoryBean;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter userBoardHttpInvokerServiceExporter() {
        SimpleHttpInvokerServiceExporter simpleHttpInvokerServiceExporter = new SimpleHttpInvokerServiceExporter();
        simpleHttpInvokerServiceExporter.setServiceInterface(UserBoardService.class);
        simpleHttpInvokerServiceExporter.setService(userBoardService());
        return simpleHttpInvokerServiceExporter;
    }

    @Bean
    public SimpleHttpInvokerServiceExporter playerIdentifierHttpInvokerServiceExporter() {
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
    public UserBoardService userBoardService() {
        return new UserBoardServiceImpl();
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

}