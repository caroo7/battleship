package gui.panels.buttons;

import gui.services.Subscriber;
import models.Player;
import gameLogic.Ship;
import gameLogic.ShipManager;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.GameInitService;
import services.shared.PlayerRegistrationService;
import services.shared.ShipGeneratorService;
import workers.UserBackgroundThread;

import javax.annotation.PostConstruct;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListenersFactory {
    private Map<Buttons, ActionListener> listeners = new HashMap<>();

    @Autowired
    private UserBackgroundThread backgroundThread;

    @Autowired
    private ShipGeneratorService shipGeneratorService;

    @Autowired
    private PlayerRegistrationService registrationService;

    @Autowired
    private GameInitService gameInitService;

    @Autowired
    private ShipManager shipManager;

    @Autowired
    private Subscriber userBoard;

    @PostConstruct
    public void initListenersFactory() {
        initListenersMap();
    }

    ActionListener getListenerForButton(Buttons button) {
        return listeners.getOrDefault(button, e -> {
        });
    }


    private void initListenersMap() {
        listeners.put(Buttons.Generate, generateShipsAction());
        listeners.put(Buttons.Start, startAction());
        listeners.put(Buttons.Rules, rulesAction());
        listeners.put(Buttons.FourShoots, fourShootsAction());
        listeners.put(Buttons.ThreeShoots, threeShootsAction());
        listeners.put(Buttons.TwoShoots, twoShootsAction());
    }

    /* TODO
    *   implement all below action methods
    * */

    private ActionListener generateShipsAction() {
        return e -> {
            Set<Ship> ships = shipGeneratorService.generateShips();
            userBoard.updateGeneratedShips(ships);
            shipManager.initShips(ships);

        };
    }

    private ActionListener startAction() {
        return e -> {
            Player player = null;
            try {
                player = registrationService.registerPlayer();
            } catch (Exception exception) {
                System.out.println("Cannot register player!");
            }

            gameInitService.initGame(player, shipManager);
            backgroundThread.execute(player);
            userBoard.updateGeneratedShips(Collections.EMPTY_SET);
            Buttons.Generate.setEnabled(false);
            Buttons.Start.setEnabled(false);
        };
    }

    private ActionListener rulesAction() {
        return e -> {
            System.out.println("Rules");
        };
    }

    private ActionListener fourShootsAction() {
        return e -> {
            System.out.println("Four shoots");
        };
    }

    private ActionListener threeShootsAction() {
        return e -> {
            System.out.println("Three shoots");
        };
    }

    private ActionListener twoShootsAction() {
        return e -> {
            System.out.println("Two shoots");
        };
    }
}
