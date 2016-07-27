package gui.panels.buttons;

import javax.swing.*;
import java.util.EnumSet;

public class ButtonsPanelFactory {

    private ListenersFactory listenersFactory = new ListenersFactory();

    public JPanel getButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        EnumSet.allOf(Buttons.class).stream().forEach(button->{
            button.addListener(listenersFactory.getListenerForButton(button));
            button.addToPanel(buttonsPanel);
        });
        return buttonsPanel;
    }
}
