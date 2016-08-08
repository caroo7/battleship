package gui.panels.buttons;

import gameLogic.Ship;
import gameLogic.ShipManager;
import gui.services.Subscriber;
import models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import services.shared.GameInitService;
import services.shared.PlayerRegistrationService;
import services.shared.ShipGeneratorService;
import services.shared.ShootService;
import workers.UserBackgroundThread;

import javax.annotation.PostConstruct;
import javax.swing.*;
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
    private ShootService shootService;

    @Autowired
    private ShipManager shipManager;

    @Autowired
    private Subscriber userBoard;

    /**
     * Add listeners for each button. Buttons have own actions which use correct services and affect on the other buttons after click
     */
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


    private ActionListener generateShipsAction() {
        return e -> {
            Set<Ship> ships = shipGeneratorService.generateShips();
            userBoard.updateGeneratedShips(ships);
            shipManager.initShips(ships);
            Buttons.Start.setEnabled(true);
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
            Buttons.FourShoots.setEnabled(true);
            Buttons.ThreeShoots.setEnabled(true);
            Buttons.TwoShoots.setEnabled(true);
        };
    }

    private ActionListener rulesAction() {
        return e -> {
            ImageIcon rulesIcon = new ImageIcon(getClass().getResource("/GameRules.jpg"));
            JOptionPane.showConfirmDialog(null, rulesIcon, "Rules", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE);
        };
    }

    private ActionListener fourShootsAction() {
        return e -> {
            shootService.randomShoot(4);
            Buttons.FourShoots.setPermanentlyDisabled();
        };
    }

    private ActionListener threeShootsAction() {
        return e -> {
            shootService.randomShoot(3);
            Buttons.ThreeShoots.setPermanentlyDisabled();
        };
    }

    private ActionListener twoShootsAction() {
        return e -> {
            shootService.randomShoot(2);
            Buttons.TwoShoots.setPermanentlyDisabled();
        };
    }
}