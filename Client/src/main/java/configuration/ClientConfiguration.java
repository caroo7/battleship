package configuration;

import gameLogic.ShipManager;
import gui.BattleshipMainFrame;
import gui.panels.boards.BoardsFactory;
import gui.panels.boards.RivalBoardGui;
import gui.panels.boards.UserBoardGui;
import gui.panels.boards.belowPanels.BelowPanelsFactory;
import gui.panels.boards.belowPanels.BelowPanelsListenerFactory;
import gui.panels.boards.belowPanels.UserBelowPanel;
import gui.panels.buttons.ButtonsPanelFactory;
import gui.panels.buttons.ListenersFactory;
import gui.services.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import services.shared.*;
import workers.UserBackgroundThread;

@Configuration
@PropertySource("classpath:config.properties")
public class ClientConfiguration {

    @Autowired
    private Environment environment;

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
    public UserBoardGui userBoard() {
        return new UserBoardGui();
    }

    @Bean
    public RivalBoardGui rivalBoard() {
        return new RivalBoardGui();
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
        String serviceURL = "http://" + environment.getRequiredProperty("host_name") + ":" + Config.SERVER_PORT + Config.SHIP_GENERATOR_SERVICE;
        httpInvoker.setServiceInterface(ShipGeneratorService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean boardMessageHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + environment.getRequiredProperty("host_name") + ":" + Config.SERVER_PORT + Config.BOARD_MESSAGE_SERVICE;
        httpInvoker.setServiceInterface(BoardsMessageService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean playerRegistrationHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + environment.getRequiredProperty("host_name") + ":" + Config.SERVER_PORT + Config.PLAYER_REGISTRATION_SERVICE;
        httpInvoker.setServiceInterface(PlayerRegistrationService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean gameInitHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + environment.getRequiredProperty("host_name") + ":" + Config.SERVER_PORT + Config.GAME_INIT_SERVICE;
        httpInvoker.setServiceInterface(GameInitService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean shootServiceHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + environment.getRequiredProperty("host_name") + ":" + Config.SERVER_PORT + Config.SHOOT_SERVICE;
        httpInvoker.setServiceInterface(ShootService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean endGameServiceHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + environment.getRequiredProperty("host_name") + ":" + Config.SERVER_PORT + Config.END_GAME_SERVICE;
        httpInvoker.setServiceInterface(EndGameService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean mainFramePositionServiceHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + environment.getRequiredProperty("host_name") + ":" + Config.SERVER_PORT + Config.MAIN_FRAME_POSITION_SERVICE;
        httpInvoker.setServiceInterface(MainFramePositionService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }


}