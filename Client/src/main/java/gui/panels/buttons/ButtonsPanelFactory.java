package gui.panels.buttons;

import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.EnumSet;

public class ButtonsPanelFactory {

    @Autowired
    private ListenersFactory listenersFactory;

    public JPanel getButtonsPanel(){
        JPanel buttonsPanel = new JPanel();
        EnumSet.allOf(Buttons.class).stream().forEach(button->{
            button.addListener(listenersFactory.getListenerForButton(button));
            button.addToPanel(buttonsPanel);
        });
        return buttonsPanel;
    }
}
