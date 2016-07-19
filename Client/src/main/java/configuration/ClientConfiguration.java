package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import services.PlayerIdentifierService;
import services.UserBoardService;

@Configuration
public class ClientConfiguration {

    @Bean
    public HttpInvokerProxyFactoryBean userBoardHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.USER_BOARD_SERVICE;
        httpInvoker.setServiceInterface(UserBoardService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

    @Bean
    public HttpInvokerProxyFactoryBean playerIdentifierHttpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean httpInvoker = new HttpInvokerProxyFactoryBean();
        String serviceURL = "http://" + Config.HOST_NAME + ":" + Config.SERVER_PORT + Config.PLAYER_IDENTIFIER_SERVICE;
        httpInvoker.setServiceInterface(PlayerIdentifierService.class);
        httpInvoker.setServiceUrl(serviceURL);
        return httpInvoker;
    }

}