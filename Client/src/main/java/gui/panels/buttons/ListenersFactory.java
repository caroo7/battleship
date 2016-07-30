package gui.panels.buttons;

import models.Player;
import models.Ship;
import models.ShipManager;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.GameInitService;
import services.shared.PlayerRegistrationService;
import services.shared.ShipGeneratorService;
import workers.UserBackgroundThread;

import javax.annotation.PostConstruct;
import java.awt.event.ActionListener;
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
            System.out.println("Generate");
            Set<Ship> ships = shipGeneratorService.generateShips();
            shipManager.initShips(ships);
        };
    }

    private ActionListener startAction() {
        return e -> {
            System.out.println("Start");
            Player player = null;
            try {
                player = registrationService.registerPlayer();
            } catch(Exception exception) {
                System.out.println("Cannot register player!");
            }

            gameInitService.initGame(player, shipManager);
            backgroundThread.execute(player);
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
