package main;

import configuration.ServerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ServerMain {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ServerConfiguration.class);
    }

}
