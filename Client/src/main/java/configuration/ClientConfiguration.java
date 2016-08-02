package configuration;

import gui.BattleshipMainFrame;
import gui.panels.boards.BoardsFactory;
import gui.panels.boards.RivalBoard;
import gui.panels.boards.UserBoard;
import gui.panels.boards.belowPanels.BelowPanelsFactory;
import gui.panels.boards.belowPanels.BelowPanelsListenerFactory;
import gui.panels.boards.belowPanels.UserBelowPanel;
import gui.panels.buttons.ButtonsPanelFactory;
import gui.panels.buttons.ListenersFactory;
import gui.services.Publisher;
import models.ShipManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import services.shared.*;
import workers.UserBackgroundThread;

@Configuration
public class ClientConfiguration {

    @Bean
    public BattleshipMainFrame mainFrame() {
        return new BattleshipMainFrame();
    }

    @Bean
    public ButtonsPanelFactory buttonsPanelFactory() {
        return new ButtonsPanelFactory();
    }

    @Bean
    public ListenersFactory listenersFactory() {
        return new ListenersFactory();
    }

    @Bean
    public UserBackgroundThread userBackgroundThread() {
        return new UserBackgroundThread();
    }

    @Bean
    public BoardsFactory boardsFactory() {
        return new BoardsFactory();
    }

    @Bean
    public UserBoard userBoard() {
        return new UserBoard();
    }

    @Bean
    public RivalBoard rivalBoard() {
        return new RivalBoard();
    }

    @Bean
    public Publisher boardPublisher() {
        return new Publisher();
    }


    @Bean
    public ShipManager shipManager() {
        return new ShipManager();
    }

    @Bean
    public UserBelowPanel belowPanel() {
        return new UserBelowPanel();
    }

    @Bean
    public BelowPanelsListenerFactory belowPanelsListenerFactory() {
        return new BelowPanelsListenerFactory();
    }

    @Bean
    public BelowPanelsFactory belowPanelsFactory() {
        return new BelowPanelsFactory();
    }

    @Bean
    public UserBelowPanel userBelowPanel() {
        return new UserBelowPanel();
    }

    @Bean
    public HttpInvokerProxyFactoryBean shipGeneratorHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.SHIP_GENERATOR_SERVICE;
        httpInvoker.setServiceInterface(ShipGeneratorService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean boardMessageHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.BOARD_MESSAGE_SERVICE;
        httpInvoker.setServiceInterface(BoardsMessageService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean playerRegistrationHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.PLAYER_REGISTRATION_SERVICE;
        httpInvoker.setServiceInterface(PlayerRegistrationService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean gameInitHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.GAME_INIT_SERVICE;
        httpInvoker.setServiceInterface(GameInitService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean shootServiceHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.SHOOT_SERVICE;
        httpInvoker.setServiceInterface(ShootService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean gameStateServiceHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.GAME_STATE_SERVICE;
        httpInvoker.setServiceInterface(GameStateService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }
}